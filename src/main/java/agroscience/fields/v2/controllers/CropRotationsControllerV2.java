package agroscience.fields.v2.controllers;

import agroscience.fields.v2.mappers.CropRotationMapperV2;
import agroscience.fields.v2.services.CropRotationServiceV2;
import generated.agroscience.fields.api.CropRotationsApi;
import generated.agroscience.fields.api.model.CropRotationDTO;
import generated.agroscience.fields.api.model.IdDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('organization') or hasRole('worker')")
public class CropRotationsControllerV2 implements CropRotationsApi {

  private final CropRotationServiceV2 cropRotationService;
  private final CropRotationMapperV2 cropRotationMapperV2;

  @Override
  public void changeCropRotation(UUID cropRotationId, CropRotationDTO updateCropRotationDTO) {
    // TODO в сервисный слой не передаём DTO
  }

  @Override
  public void deleteCropRotation(UUID cropRotationId) {
    // TODO Не удаляем, архивируем
  }

  @Override
  public List<CropRotationDTO> getCropRotations(UUID contourID) {
    return null; // TODO
  }

  @Override
  public IdDTO saveCropRotation(UUID contourId, CropRotationDTO cropRotationDTO) {
    var cropRotation = cropRotationMapperV2.map(cropRotationDTO);
    var cropRotationEntity = cropRotationService.save(contourId, cropRotation);
    return new IdDTO(cropRotationEntity.getId());
  }

}

