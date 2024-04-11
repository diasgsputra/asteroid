package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Asteroids {
  private String id;
  private String name;
  @JsonProperty("nasa_jpl_url")
  private String nasaJplUrl;
  @JsonProperty("absolute_magnitude_h")
  private double absoluteMagnitudeH;
  @JsonProperty("estimated_diameter")
  private EstimatedDiameter estimatedDiameter;
  @JsonProperty("is_potentially_hazardous_asteroid")
  private boolean isPotentiallyHazardousAsteroid;
  @JsonProperty("close_approach_data")
  private List<CloseApproachData> closeApproachData;
}
