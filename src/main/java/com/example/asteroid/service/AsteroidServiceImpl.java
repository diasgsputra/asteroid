package com.example.asteroid.service;

import com.example.asteroid.entity.AsteroidMapping;
import com.example.asteroid.entity.CloseApproachData;
import com.example.asteroid.entity.Asteroids;
import com.example.asteroid.entity.NearEarthObjects;
import com.example.asteroid.repository.AsteroidRepository;
import com.example.asteroid.response.AsteroidsResponse;
import com.example.asteroid.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.asteroid.exception.BadRequestException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AsteroidServiceImpl implements AsteroidService {
  @Autowired
  private ObjectMapper objectMapper;
  private UriComponentsBuilder builder;
  @Value("${nasa.api.url}")
  private String apiUrl;

  @Value("${nasa.api.key}")
  private String apiKey;
  private HttpEntity<String> entity;
  @Autowired
  private AsteroidRepository asteroidRepository;

  @Override
  public List<AsteroidsResponse> getClosestAsteroid(String startDate, String endDate) {
    //get data from api nasa
    List<Asteroids> listNeo = fetchDataFromNasa(startDate, endDate);

    //sort and get only 10 closest asteroids
    List<Asteroids> sortedAsteroids = listNeo.stream()
        .sorted(Comparator.comparingDouble(this::getClosestDistance))
        .limit(10)
        .toList();

    return sortedAsteroids.stream()
        .map(AsteroidsResponse::new)
        .collect(Collectors.toList());
  }

  @Override
  public String mappingOneYearAsteroid(String year) {
    LocalDate initialDate = LocalDate.of(Integer.parseInt(year), 1, 1);
    LocalDate finishDate = initialDate.plusYears(1).minusDays(1);
    LocalDate startDate = initialDate;
    LocalDate nextEndDate;
    List<Asteroids> listNeo = new ArrayList<>();

    while (startDate.isBefore(finishDate)) {
      nextEndDate = startDate.plusDays(6).isBefore(finishDate) ? startDate.plusDays(6) : finishDate;
      listNeo.addAll(fetchDataFromNasa(startDate.toString(), nextEndDate.toString()));
      startDate = nextEndDate.plusDays(1);
    }

    List<Asteroids> sortedAsteroids = listNeo.stream()
        .sorted(Comparator.comparingDouble(this::getClosestDistance))
        .toList();

    List<AsteroidMapping> mappedAsteroid = sortedAsteroids.stream()
        .map(AsteroidMapping::new)
        .toList();

    //asteroid that already exist in database won't be saved because the ID must be unique
    asteroidRepository.saveAll(mappedAsteroid);

    return "Data saved";
  }

  @Override
  public BaseResponse getAsteroidByDistance(Double distance){
    List<AsteroidMapping> asteroids = asteroidRepository.getAsteroidByDistance(distance);
    Long countAsteroids = asteroidRepository.countAsteroidByDistance(distance);

    return new BaseResponse(asteroids.stream()
        .map(AsteroidsResponse::new)
        .collect(Collectors.toList()),countAsteroids);
  }

  private List<Asteroids> fetchDataFromNasa(String startDate, String endDate) {
    RestTemplate restTemplate = new RestTemplate();
    builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
        .queryParam("api_key", apiKey)
        .queryParam("start_date", startDate)
        .queryParam("end_date", endDate);
    String uriString = builder.toUriString();

    try {
      List<Asteroids> listNeo = new ArrayList<>();
      ResponseEntity<String> result = restTemplate.exchange(uriString, HttpMethod.GET, entity, String.class);
      NearEarthObjects neo = objectMapper.readValue(result.getBody(), NearEarthObjects.class);
      if (neo != null && neo.getNearEarthObjects() != null) {
        for (Map.Entry<String, List<Asteroids>> entry : neo.getNearEarthObjects().entrySet()) {
          List<Asteroids> asteroids = entry.getValue();
          if (asteroids != null) {
            listNeo.addAll(asteroids);
          }
        }
        return listNeo;
      }
    } catch (Exception e) {
      throw new BadRequestException("Date cannot be more than 7 days");
    }
    return new ArrayList<>();
  }

  private double getClosestDistance(Asteroids asteroid) {
    if (asteroid.getCloseApproachData() != null && !asteroid.getCloseApproachData().isEmpty()) {
      CloseApproachData closestApproach = asteroid.getCloseApproachData().get(0);
      try {
        return Double.parseDouble(closestApproach.getMissDistance().getKilometers());
      } catch (NumberFormatException e) {
        return Double.MAX_VALUE;
      }
    }
    return Double.MAX_VALUE;
  }
}
