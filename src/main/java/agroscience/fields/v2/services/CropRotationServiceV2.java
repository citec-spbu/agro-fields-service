package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.CropRotationV2;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.CropRotationRepositoryV2;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropRotationServiceV2 extends DefaultService {

  private final CropRotationRepositoryV2 cropRotationRepository;
  private final ContoursRepository contoursRepository;

  public CropRotationV2 save(UUID contourId, CropRotationV2 cropRotation) {
    var contour = getOrThrow(contourId, contoursRepository::findById);
    cropRotation.setContour(contour);
    var cropRotationId = UUID.randomUUID();
    cropRotation.setCropRotationId(cropRotationId);
    return cropRotationRepository.save(cropRotation);
  }

  public CropRotationV2 getCropRotation(UUID cropRotationId) {
    return cropRotationRepository.getReferenceById(cropRotationId);
  }
}
