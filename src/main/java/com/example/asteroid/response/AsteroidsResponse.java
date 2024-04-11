package com.example.asteroid.response;

import com.example.asteroid.entity.CloseApproachData;
import com.example.asteroid.entity.Asteroids;
import lombok.Data;

@Data
public class AsteroidsResponse {
  private Long id;
  private String name;
  private boolean isPotentiallyHazardous;
  private double distance;
  private double velocity;

  public AsteroidsResponse(Asteroids neo) {
    this.id = Long.valueOf(neo.getId());
    this.name = neo.getName();
    this.isPotentiallyHazardous = neo.isPotentiallyHazardousAsteroid();

    //because approach data is list form, get the first element
    CloseApproachData closeApproachData = neo.getCloseApproachData().get(0);
    this.distance = Double.parseDouble(closeApproachData.getMissDistance().getKilometers());
    this.velocity = Double.parseDouble(closeApproachData.getRelativeVelocity().getKilometersPerHour());
  }
}
