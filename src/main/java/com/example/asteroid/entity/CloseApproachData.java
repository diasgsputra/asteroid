package com.example.asteroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CloseApproachData {
  @JsonProperty("close_approach_date")
  private String closeApproachDate;
  @JsonProperty("close_approach_date_full")
  private String closeApproachDateFull;
  @JsonProperty("relative_velocity")
  private RelativeVelocity relativeVelocity;
  @JsonProperty("miss_distance")
  private MissDistance missDistance;
  @JsonProperty("orbiting_body")
  private String orbitingBody;
}
