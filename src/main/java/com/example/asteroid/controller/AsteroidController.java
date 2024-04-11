package com.example.asteroid.controller;

import com.example.asteroid.response.AsteroidsResponse;
import com.example.asteroid.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AsteroidController {
  @Autowired
  private AsteroidService asteroidService;

  @GetMapping("/asteroids")
  public ResponseEntity<List<AsteroidsResponse>> getAsteroids() {
    List<AsteroidsResponse> asteroids = asteroidService.getClosestAsteroid();
    return new ResponseEntity<>(asteroids, HttpStatus.OK);
  }
}
