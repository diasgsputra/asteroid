package com.example.asteroid.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Data
@Entity
@Table(name = "asteroids")
public class AsteroidMapping {

  @Id
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "is_potentially_hazardous")
  private boolean isPotentiallyHazardous;
  @Column(name = "distance")
  private double distance;
  @Column(name = "velocity")
  private double velocity;

  public AsteroidMapping() {
  }
  public AsteroidMapping(Asteroids neo) {
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
