package org.folio.poc.specifications.server.service;

import java.util.NoSuchElementException;
import org.folio.poc.specifications.core.domain.ValidationException;
import org.folio.poc.specifications.core.service.Validator;
import org.folio.poc.specifications.dto.MarcRecord;
import org.folio.poc.specifications.dto.RecordSpecification;
import org.folio.poc.specifications.dto.RecordType;
import org.folio.poc.specifications.server.respository.RecordSpecificationsRepository;
import org.springframework.stereotype.Service;

@Service
public class RecordSpecificationsService {

  private final RecordSpecificationsRepository repository;
  private final Validator validator;

  public RecordSpecificationsService(RecordSpecificationsRepository repository) {
    this.repository = repository;
    validator = new Validator();
  }

  public RecordSpecification get(RecordType recordType) {
    return repository.get(recordType)
      .orElseThrow(() -> new NoSuchElementException("RecordSpecification not found for record type: " + recordType));
  }

  public void save(RecordSpecification specification) {
    repository.save(specification);
  }

  public void validate(MarcRecord marcRecord) {
    var specification = get(marcRecord.getType());
    var errors = validator.validate(marcRecord, specification);

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}
