package com.example.asteroid.controller;

import com.example.asteroid.response.AsteroidsResponse;
import com.example.asteroid.response.BaseResponse;
import com.example.asteroid.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AsteroidController {
  @Autowired
  private AsteroidService asteroidService;

  @GetMapping("/asteroids")
  public ResponseEntity<BaseResponse> getAsteroids(@RequestParam(name="start_date", defaultValue = "2024-04-12") String startDate,
                                                              @RequestParam(name="end_date", defaultValue = "2024-04-13") String endDate) {
    List<AsteroidsResponse> asteroids = asteroidService.getClosestAsteroid(startDate, endDate);
    BaseResponse response = new BaseResponse(asteroids);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/one-year-asteroids")
  public ResponseEntity<BaseResponse> getAsteroids(@RequestParam(name="year", defaultValue = "2023") String year) {
    String asteroids = asteroidService.getAsteroidInYear(year);
    BaseResponse response = new BaseResponse(asteroids);
    return ResponseEntity.ok(response);
  }
}
