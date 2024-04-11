package com.example.asteroid.entity;

import lombok.Data;

@Data
public class EstimatedDiameter {
  private Diameter kilometers;
  private Diameter meters;
  private Diameter miles;
  private Diameter feet;
}
