package com.example.asteroid.response;

import com.example.asteroid.entity.CloseApproachData;
import com.example.asteroid.entity.Asteroids;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Data
public class AsteroidsResponse {
  private Long id;
  private String name;
  @JsonProperty("is_potentially_hazardous")
  private boolean isPotentiallyHazardous;
  private double distance;
  private double velocity;

  public AsteroidsResponse(Asteroids neo) {
    this.id = Long.valueOf(neo.getId());
    this.name = neo.getName();
    this.isPotentiallyHazardous = neo.isPotentiallyHazardousAsteroid();

    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.CEILING);
    //because approach data is list form, get the first element
    CloseApproachData closeApproachData = neo.getCloseApproachData().get(0);
    this.distance = Double.parseDouble(df.format(Double.parseDouble(closeApproachData.getMissDistance().getKilometers())));
    this.velocity = Double.parseDouble(df.format(Double.parseDouble(closeApproachData.getRelativeVelocity().getKilometersPerHour())));

  }
}
