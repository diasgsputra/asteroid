package com.example.asteroid.response;

import lombok.Data;

@Data
public class BaseResponse {
  private String status;
  private Long total_result;
  private Object data;
  public BaseResponse(Object data){
    this.data = data;
    this.status = "Success";
  }

  public BaseResponse(Object data, Long totalResult){
    this.data = data;
    this.status = "Success";
    this.total_result = totalResult;
  }
}
