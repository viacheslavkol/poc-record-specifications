package org.folio.poc.specifications.server.controller;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.folio.poc.specifications.core.domain.ValidationException;
import org.folio.poc.specifications.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception) {
    var errors = exception.getErrors();
    var errorResponse = new ErrorResponse().errors(errors).totalRecords(errors.size());
    return new ResponseEntity<>(errorResponse, UNPROCESSABLE_ENTITY);
  }
}
