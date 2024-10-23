package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.soilcompositions.RequestSoilComposition;
import agroscience.fields.v2.mappers.SoilCompositionMapper;
import agroscience.fields.v2.services.SoilCompositionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v2/fields/soilCompositions") // TODO Replace with api/v2/fields/soil-compositions
public class SoilCompositionsController {

  private final SoilCompositionMapper soilCompositionMapper;
  private final SoilCompositionsService soilCompositionsService;

  @PostMapping
  // TODO Use @PreAuthorize("hasRole('organization') or hasRole('worker')")
  // TODO Return json, not string. Use dto
  public String save(@Valid @RequestBody RequestSoilComposition requestSoilComposition) {
    var a = soilCompositionMapper.map(requestSoilComposition); //TODO Use model mapper
    return soilCompositionsService.save(a).toString();
  }

}
