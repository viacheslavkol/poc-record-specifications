package org.folio.poc.specifications.server.controller;

import lombok.RequiredArgsConstructor;
import org.folio.poc.specifications.dto.MarcRecord;
import org.folio.poc.specifications.rest.resource.RecordValidationApi;
import org.folio.poc.specifications.server.service.RecordSpecificationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ValidationController implements RecordValidationApi {

  private final RecordSpecificationsService service;

  @Override
  public ResponseEntity<Void> recordValidation(MarcRecord body) {
    service.validate(body);

    return ResponseEntity.ok().build();
  }
}
