package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.CropRotationV2;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.CropRotationRepositoryV2;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropRotationServiceV2 extends DefaultService {

  private final CropRotationRepositoryV2 cropRotationRepository;
  private final ContoursRepository contoursRepository;

  public CropRotationV2 save(UUID contourId, CropRotationV2 cropRotation) {
    Contour contour = getOrThrow(contourId, contoursRepository::findById);
    cropRotation.setContour(contour);
    return cropRotationRepository.save(cropRotation);
  }

  public void update(UUID cropRotationId, CropRotationV2 updateCropRotation) {
    CropRotationV2 cropRotation = getOrThrow(cropRotationId, cropRotationRepository::findById);
    checkArchived(cropRotationId, cropRotation);
    cropRotation.setDescription(updateCropRotation.getDescription());
    cropRotation.setStartDate(updateCropRotation.getStartDate());
    cropRotation.setEndDate(updateCropRotation.getEndDate());
    cropRotationRepository.save(cropRotation);
  }

  public void archive(UUID cropRotationId) {
    CropRotationV2 cropRotation = getOrThrow(cropRotationId, cropRotationRepository::findById);
    cropRotation.setArchived(true);
    cropRotationRepository.save(cropRotation);
  }

  public List<CropRotationV2> getAllByContourId(UUID contourId) {
    return cropRotationRepository.getAllByContourIdAndArchivedIsFalse(contourId);
  }
}
