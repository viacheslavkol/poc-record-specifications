package org.folio.poc.specifications.core.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.folio.poc.specifications.dto.RecordSpecification;
import org.folio.poc.specifications.dto.RecordType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "record-specifications", url = "http://localhost:8080/record-specifications")
public interface RecordSpecificationsClient {

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  RecordSpecification getRecordSpecification(@RequestParam("recordType") RecordType type);
}
