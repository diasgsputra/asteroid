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
  public ResponseEntity<BaseResponse> getAsteroids(@RequestParam("start_date") String startDate,
                                                              @RequestParam("end_date") String endDate) {
    List<AsteroidsResponse> asteroids = asteroidService.getClosestAsteroid(startDate, endDate);
    BaseResponse response = new BaseResponse(asteroids);
    return ResponseEntity.ok(response);
  }
}
