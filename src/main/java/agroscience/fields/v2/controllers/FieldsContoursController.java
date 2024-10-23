package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.fields.RequestFieldv2;
import agroscience.fields.v2.mappers.FieldMapperV2;
import agroscience.fields.v2.services.FieldsContoursService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "api/v2/fields/fields")
@RequiredArgsConstructor
public class FieldsContoursController {

  private final FieldMapperV2 fieldMapperV2;
  private final FieldsContoursService fieldService;

  @PostMapping
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public String save(@Valid @RequestBody RequestFieldv2 field) {
    var a = fieldMapperV2.map(field);
    return fieldService.save(a).toString();
  }

}
