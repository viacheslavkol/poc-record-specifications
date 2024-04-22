package org.folio.poc.specifications.server.controller;

import lombok.RequiredArgsConstructor;
import org.folio.poc.specifications.dto.RecordSpecification;
import org.folio.poc.specifications.dto.RecordType;
import org.folio.poc.specifications.rest.resource.RecordSpecificationsApi;
import org.folio.poc.specifications.server.service.RecordSpecificationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecordSpecificationsController implements RecordSpecificationsApi {

  private final RecordSpecificationsService service;

  @Override
  public ResponseEntity<RecordSpecification> getRecordSpecifications(RecordType recordType) {
    var specification = service.get(recordType);
    return ResponseEntity.ok(specification);
  }

  @Override
  public ResponseEntity<Void> postRecordSpecifications(RecordSpecification body) {
    service.save(body);
    return ResponseEntity.ok().build();
  }
}
