package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NearEarthObjects {
  @JsonProperty("near_earth_objects")
  private NearEarthObjectsDate nearEarthObjects;
}

