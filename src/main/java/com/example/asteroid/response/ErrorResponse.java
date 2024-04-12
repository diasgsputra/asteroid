package com.example.asteroid.response;

import lombok.Data;

@Data
public class ErrorResponse {
  private long timestamp;
  private int status;
  private String error;
  private String message;
  private String path;
}
