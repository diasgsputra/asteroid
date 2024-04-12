package com.example.asteroid.response;

import lombok.Data;

@Data
public class BaseResponse {
  private String status;
  private Object data;
  public BaseResponse(Object data){
    this.data = data;
    this.status = "Success";
  }
}
