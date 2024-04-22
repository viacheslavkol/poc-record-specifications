package org.folio.poc.specifications.core.service;

import java.util.LinkedList;
import java.util.List;
import org.folio.poc.specifications.dto.MarcRecord;
import org.folio.poc.specifications.dto.RecordSpecification;

public class Validator {

  public List<String> validate(MarcRecord marcRecord, RecordSpecification specification) {
    var errors = new LinkedList<String>();
    if (!specification.getType().equals(marcRecord.getType())) {
      errors.add("Record type not valid");
      return errors;
    }

    for (var fieldRule : specification.getFieldRules()) {
      var field = marcRecord.getFields().stream()
        .filter(recordField -> fieldRule.getTag().equals(recordField.getTag()))
        .findFirst()
        .orElse(null);
      if (field == null) {
        errors.add("Required field %s is missing".formatted(fieldRule.getTag()));
        continue;
      }

      var missingRequiredSubfields = new LinkedList<>(fieldRule.getRequiredSubfields());
      missingRequiredSubfields.removeAll(field.getSubfields());
      if (!missingRequiredSubfields.isEmpty()) {
        errors.add("Field %s is missing required subfields: %s"
          .formatted(fieldRule.getTag(), missingRequiredSubfields));
      }
    }

    return errors;
  }

  static class ValidationException extends RuntimeException {
    ValidationException(String message) {
      super(message);
    }
  }
}
