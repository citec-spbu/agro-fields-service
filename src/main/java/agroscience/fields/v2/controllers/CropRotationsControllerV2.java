package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.croprotation.RequestCropRotationV2;
import agroscience.fields.v2.dto.croprotation.ResponseCropRotationV2;
import agroscience.fields.v2.entities.CropRotationV2;
import agroscience.fields.v2.services.CropRotationServiceV2;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(path = "api/v2/fields/crop-rotations")
@RequiredArgsConstructor
public class CropRotationsControllerV2 {

  private final CropRotationServiceV2 cropRotationService;
  private final ModelMapper modelMapper;

  @PostMapping
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public ResponseCropRotationV2 save(@Valid @RequestBody RequestCropRotationV2 requestCropRotation) {
    var cropRotation = modelMapper.map(requestCropRotation, CropRotationV2.class);
    var cropRotationEntity = cropRotationService.save(cropRotation);
    return modelMapper.map(cropRotationEntity, ResponseCropRotationV2.class);
  }

  @GetMapping()
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public CropRotationV2 get(@Valid UUID cropRotationId) {
    return cropRotationService.getCropRotation(cropRotationId);
  }

}

