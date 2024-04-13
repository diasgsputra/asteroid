package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NearEarthObjects {
  @JsonProperty("near_earth_objects")
  private Map<String, List<Asteroids>> nearEarthObjects;
}

