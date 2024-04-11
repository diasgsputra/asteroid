package com.example.asteroid.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MissDistance {
  private String astronomical;
  private String lunar;
  private String kilometers;
  private String miles;
}
