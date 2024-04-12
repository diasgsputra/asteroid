package com.example.asteroid.service;
import com.example.asteroid.entity.Asteroids;
import com.example.asteroid.entity.CloseApproachData;
import com.example.asteroid.entity.MissDistance;
import com.example.asteroid.entity.NearEarthObjects;
import com.example.asteroid.exception.BadRequestException;
import com.example.asteroid.support.TestingConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest(classes = TestingConfiguration.class)
public class AsteroidServiceImplTest {

  @MockBean
  private RestTemplate restTemplate;
  @MockBean
  private UriComponentsBuilder builder;
  @MockBean
  private ObjectMapper objectMapper;

  private AsteroidServiceImpl asteroidServiceImpl;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    asteroidServiceImpl = new AsteroidServiceImpl();
  }

  @Test
  public void getClosestAsteroid_thenSuccess() throws Exception {
    NearEarthObjects neo = new NearEarthObjects();
    Map<String, List<Asteroids>> neoMap = new HashMap<>();
    List<Asteroids> asteroidsList = new ArrayList<>();
    Asteroids asteroid = new Asteroids();
    CloseApproachData approachData= new CloseApproachData();
    MissDistance missDistance = new MissDistance();
    missDistance.setKilometers("1000");
    approachData.setMissDistance(missDistance);
    asteroid.setCloseApproachData(List.of(approachData));
    asteroidsList.add(asteroid);
    neoMap.put(anyString(), asteroidsList);
    neo.setNearEarthObjects(neoMap);

    ResponseEntity<String> responseEntity = new ResponseEntity<>(objectMapper.writeValueAsString(neo), HttpStatus.OK);

    Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class)))
        .thenReturn(responseEntity);

  }

  @Test
  public void getClosestAsteroid_thenFail() {
    Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class)))
        .thenThrow(new RuntimeException("API error"));

    Assertions.assertThrows(BadRequestException.class, () -> {
      asteroidServiceImpl.getClosestAsteroid("2024-04-12", "2024-04-19");
    });
  }
}