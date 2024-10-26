package agroscience.fields.v2.controllers;

import agroscience.fields.v2.mappers.SoilCompositionMapper;
import agroscience.fields.v2.services.SoilCompositionsService;
import generated.agroscience.fields.api.SoilCompositionsApi;
import generated.agroscience.fields.api.model.IdDTO;
import generated.agroscience.fields.api.model.SoilCompositionDTO;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('organization') or hasRole('worker')")
public class SoilCompositionsController implements SoilCompositionsApi {

  private final SoilCompositionMapper soilCompositionMapper;
  private final SoilCompositionsService soilCompositionsService;

  @Override
  public SoilCompositionDTO getSoilComposition(UUID soilCompositionId) {
    return soilCompositionMapper.map(soilCompositionsService.findById(soilCompositionId));
  }

  @Override
  public IdDTO saveSoilComposition(SoilCompositionDTO soilCompositionDTO) {
    var soilComposition = soilCompositionMapper.map(soilCompositionDTO);
    var soilCompositionEntity = soilCompositionsService.save(soilComposition);
    return new IdDTO(soilCompositionEntity.getSoilCompositionId());
  }

}