package com.example.asteroid.handler;

import com.example.asteroid.exception.BadRequestException;
import com.example.asteroid.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTimestamp(System.currentTimeMillis());
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
