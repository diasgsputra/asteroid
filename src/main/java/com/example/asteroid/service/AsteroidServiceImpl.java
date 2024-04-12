package com.example.asteroid.service;

import com.example.asteroid.entity.CloseApproachData;
import com.example.asteroid.entity.Asteroids;
import com.example.asteroid.entity.NearEarthObjects;
import com.example.asteroid.response.AsteroidsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.asteroid.exception.BadRequestException;

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
  private static final String URI = "https://api.nasa.gov/neo/rest/v1/feed";
  private HttpEntity<String> entity;

  @Override
  public List<AsteroidsResponse> getClosestAsteroid(String startDate, String endDate) {
    RestTemplate restTemplate = new RestTemplate();
    builder  = UriComponentsBuilder.fromHttpUrl(URI)
        .queryParam("api_key","2zHnloMyRZOOUBHBfma0ih4Deev6aJsYsZyGDJDc")
        .queryParam("start_date",startDate)
        .queryParam("end_date",endDate);
    String uriString = builder.toUriString();
    //after get the response from nasa api in String, then mapping into our entity
    List<Asteroids> listNeo = new ArrayList<>();
    try {
      ResponseEntity<String> result = restTemplate.exchange(uriString,HttpMethod.GET,entity,String.class);
      NearEarthObjects neo = objectMapper.readValue(result.getBody(), NearEarthObjects.class);
      if (neo != null && neo.getNearEarthObjects() != null) {
        for (Map.Entry<String, List<Asteroids>> entry : neo.getNearEarthObjects().entrySet()) {
          List<Asteroids> asteroids = entry.getValue();
          if (asteroids != null) {
            listNeo.addAll(asteroids);
          }
        }
      }
    } catch (Exception e) {
      throw new BadRequestException("Date cannot more than 7 days");
    }

    //sort and pick only 10 elements
    List<Asteroids> sortedAsteroids = listNeo.stream()
        .sorted(Comparator.comparingDouble(asteroid -> {
          if (asteroid.getCloseApproachData() != null && !asteroid.getCloseApproachData().isEmpty()) {
            CloseApproachData closestApproach = asteroid.getCloseApproachData().get(0);
            try {
              return Double.parseDouble(closestApproach.getMissDistance().getKilometers());
            } catch (NumberFormatException e) {
              return Double.MAX_VALUE;
            }
          }
          return Double.MAX_VALUE;
        }))
        .limit(10)
        .collect(Collectors.toList());

    //Mapping to our response class
    List<AsteroidsResponse> neoApiResponses = sortedAsteroids.stream()
        .map(AsteroidsResponse::new)
        .collect(Collectors.toList());

    return neoApiResponses;

  }

}
