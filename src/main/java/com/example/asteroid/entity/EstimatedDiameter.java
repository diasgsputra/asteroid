package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EstimatedDiameter {
  private Diameter kilometers;
  private Diameter meters;
  private Diameter miles;
  private Diameter feet;
}
