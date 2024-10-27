package agroscience.fields.v2.controllers;

import agroscience.fields.v2.mappers.SoilCompositionMapper;
import agroscience.fields.v2.services.SoilCompositionsService;
import generated.agroscience.fields.api.SoilCompositionsApi;
import generated.agroscience.fields.api.model.IdDTO;
import generated.agroscience.fields.api.model.SoilCompositionDTO;
import java.util.List;
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
  public void changeSoilComposition(UUID soilCompositionId, SoilCompositionDTO soilCompositionDTO) {
    // TODO в сервисный слой не передаём DTO
  }

  @Override
  public void deleteSoilComposition(UUID soilCompositionId) {
    // TODO Не удаляем, архивируем
  }

  @Override
  public List<SoilCompositionDTO> getSoilCompositions(UUID contourId) {
    return null; // TODO
  }

  @Override
  public IdDTO saveSoilComposition(UUID contourId, SoilCompositionDTO soilCompositionDTO) {
    var soilComposition = soilCompositionMapper.map(soilCompositionDTO);
    var soilCompositionEntity = soilCompositionsService.save(contourId, soilComposition);
    return new IdDTO(soilCompositionEntity.getSoilCompositionId());
  }

}