package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.SoilComposition;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.SoilCompositionsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoilCompositionsService extends DefaultService {

  private final SoilCompositionsRepository soilCompositionsRepository;
  private final ContoursRepository contoursRepository;

  public SoilComposition save(UUID contourId, SoilComposition soilComposition) {
    var contour = getOrThrow(contourId, contoursRepository::findById);
    soilComposition.setContour(contour);
    return soilCompositionsRepository.save(soilComposition);
  }

  public SoilComposition findById(UUID soilCompositionId) {
    return soilCompositionsRepository.findById(soilCompositionId)
            .orElseThrow(() -> new EntityNotFoundException("SoilComposition not found"));
  }

  public void archive(UUID soilCompositionId) {
    SoilComposition soilComposition = getOrThrow(soilCompositionId, soilCompositionsRepository::findById);
    soilComposition.setArchived(true);
    soilCompositionsRepository.save(soilComposition);
  }

  public void updateSoilComposition(UUID soilCompositionId, SoilComposition updatedSoilComposition) {
    SoilComposition oldSoilComposition = getOrThrow(soilCompositionId, soilCompositionsRepository::findById);
    checkArchived(soilCompositionId, oldSoilComposition);
    updatedSoilComposition.setSoilCompositionId(oldSoilComposition.getSoilCompositionId());
    updatedSoilComposition.setContour(oldSoilComposition.getContour());
    soilCompositionsRepository.save(updatedSoilComposition);
  }

  public List<SoilComposition> getAllSoilCompositions(UUID contourId) {
    Contour contour = getOrThrow(contourId, contoursRepository::findById);
    return soilCompositionsRepository.findAllByContourAndArchivedIsFalse(contour);
  }

}
