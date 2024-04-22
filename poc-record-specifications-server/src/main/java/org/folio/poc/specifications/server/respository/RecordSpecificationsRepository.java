package org.folio.poc.specifications.server.respository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.folio.poc.specifications.dto.RecordSpecification;
import org.folio.poc.specifications.dto.RecordType;
import org.springframework.stereotype.Repository;

@Repository
public class RecordSpecificationsRepository {

  private final Map<RecordType, RecordSpecification> database;

  public RecordSpecificationsRepository() {
    this.database = new HashMap<>();
  }

  public Optional<RecordSpecification> get(RecordType recordType) {
    return Optional.ofNullable(database.get(recordType));
  }

  public void save(RecordSpecification specification) {
    database.put(specification.getType(), specification);
  }
}
