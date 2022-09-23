package com.tamkiraly.linkedinlight.configurations;

import com.tamkiraly.linkedinlight.exceptions.LinkedInLightException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(LinkedInLightException.class)
  public ResponseEntity<String> returnSpecificErrorDTO(LinkedInLightException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
