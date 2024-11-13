package agroscience.fields.v2.controllers;

import agroscience.fields.v2.entities.CropRotationV2;
import agroscience.fields.v2.mappers.CropRotationMapperV2;
import agroscience.fields.v2.services.CropRotationServiceV2;
import generated.agroscience.fields.api.CropRotationsApi;
import generated.agroscience.fields.api.model.CropRotationDTO;
import generated.agroscience.fields.api.model.IdDTO;
import generated.agroscience.fields.api.model.UpdateCropRotationDTO;
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
  public void changeCropRotation(UUID cropRotationId, UpdateCropRotationDTO updateCropRotationDTO) {
    CropRotationV2 updateCropRotation = cropRotationMapperV2.map(updateCropRotationDTO);
    cropRotationService.update(cropRotationId, updateCropRotation);
  }

  @Override
  public void deleteCropRotation(UUID cropRotationId) {
    //Не удаляем, архивируем
    cropRotationService.delete(cropRotationId);
  }

  @Override
  public List<CropRotationDTO> getCropRotations(UUID contourID) {
    return cropRotationMapperV2.map(cropRotationService.getAllByContourId(contourID));
  }

  @Override
  public IdDTO saveCropRotation(UUID contourId, CropRotationDTO cropRotationDTO) {
    CropRotationV2 cropRotation = cropRotationMapperV2.map(cropRotationDTO);
    CropRotationV2 cropRotationEntity = cropRotationService.save(contourId, cropRotation);
    return new IdDTO(cropRotationEntity.getId());
  }

}
