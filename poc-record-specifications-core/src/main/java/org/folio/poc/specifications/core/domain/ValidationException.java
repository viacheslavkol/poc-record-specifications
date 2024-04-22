package org.folio.poc.specifications.core.domain;

import java.util.List;

public class ValidationException extends RuntimeException {

  private final List<String> errors;

  public ValidationException(List<String> errors) {
    this.errors = errors;
  }

  public List<String> getErrors() {
    return errors;
  }
}
