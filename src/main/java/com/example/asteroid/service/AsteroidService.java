package com.example.asteroid.service;

import com.example.asteroid.response.AsteroidsResponse;
import com.example.asteroid.response.BaseResponse;

import java.util.List;

public interface AsteroidService {
  List<AsteroidsResponse> getClosestAsteroid(String startDate, String endDate);
  String mappingOneYearAsteroid(String year);
  BaseResponse getAsteroidByDistance(Double distance);
}
