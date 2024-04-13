package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Diameter {
  @JsonProperty("estimated_diameter_min")
  private double min;

  @JsonProperty("estimated_diameter_max")
  private double max;
}
