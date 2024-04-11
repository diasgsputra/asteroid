package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NearEarthObjects {
  @JsonProperty("near_earth_objects")
  private Map<String, List<Asteroids>> nearEarthObjects;
}

