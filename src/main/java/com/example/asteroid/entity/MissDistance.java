package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MissDistance {
  private String astronomical;
  private String lunar;
  private String kilometers;
  private String miles;
}
