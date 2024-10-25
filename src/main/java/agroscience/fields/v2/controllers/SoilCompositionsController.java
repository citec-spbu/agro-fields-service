package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.IdDTO;
import agroscience.fields.v2.dto.SoilCompositionDTO;
import agroscience.fields.v2.entities.SoilComposition;
import agroscience.fields.v2.services.SoilCompositionsService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v2/fields/soil-compositions")
public class SoilCompositionsController {

  private final ModelMapper modelMapper;
  private final SoilCompositionsService soilCompositionsService;

  @PostMapping
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public IdDTO save(@Valid @RequestBody SoilCompositionDTO request) {
    var soilComposition = modelMapper.map(request, SoilComposition.class);
    var soilCompositionEntity = soilCompositionsService.save(soilComposition);
    return new IdDTO(soilCompositionEntity.getSoilCompositionId().toString());
  }

  @GetMapping
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public SoilComposition get(@Valid @RequestParam String soilCompositionId) {
    return soilCompositionsService.findById(UUID.fromString(soilCompositionId));
  }
}
