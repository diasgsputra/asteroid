package com.example.asteroid.service;

import com.example.asteroid.response.AsteroidsResponse;

import java.util.List;

public interface AsteroidService {
  List<AsteroidsResponse> getClosestAsteroid();
}
