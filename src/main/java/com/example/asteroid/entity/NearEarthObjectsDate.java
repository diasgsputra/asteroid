package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NearEarthObjectsDate {
  @JsonProperty("2015-09-08")
  private List<Asteroids> asteroids;
}
