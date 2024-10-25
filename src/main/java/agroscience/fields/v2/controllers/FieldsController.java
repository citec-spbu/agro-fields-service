package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.FieldDTO;
import agroscience.fields.v2.dto.IdDTO;
import agroscience.fields.v2.dto.allrequests.ArField;
import agroscience.fields.v2.mappers.FieldMapperV2;
import agroscience.fields.v2.services.FieldsService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "api/v2/fields/fields")
@RequiredArgsConstructor
public class FieldsController {

  private final FieldMapperV2 fieldMapperV2;
  private final FieldsService fieldService;
  private final ModelMapper modelMapper;

  @PostMapping
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public IdDTO save(@Valid @RequestBody FieldDTO field) {
    var fieldEntity = fieldMapperV2.map(field);
    var savedFieldEntity = fieldService.save(fieldEntity);
    return new IdDTO(savedFieldEntity.getFieldId().toString());
  }

  @GetMapping
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public FieldDTO get(@Valid @RequestParam String fieldId) {
    return fieldMapperV2.map(fieldService.findById(UUID.fromString(fieldId)));
  }

  @GetMapping("/season")
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public List<ArField> findAll(@Valid @RequestParam String id) {
    return fieldMapperV2.map(fieldService.findAll(UUID.fromString(id)));
  }

}
