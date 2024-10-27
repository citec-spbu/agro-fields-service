package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.SoilComposition;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.SoilCompositionsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoilCompositionsService extends DefaultService {

  private final SoilCompositionsRepository soilCompositionsRepository;
  private final ContoursRepository contoursRepository;

  public SoilComposition save(UUID contourId, SoilComposition soilComposition) {
    var contour = getOrThrow(contoursRepository.findById(contourId), Contour.class);
    soilComposition.setContour(contour);
    var soilCompositionId = UUID.randomUUID();
    soilComposition.setSoilCompositionId(soilCompositionId);
    return soilCompositionsRepository.save(soilComposition);
  }

  public SoilComposition findById(UUID soilCompositionId) {
    return soilCompositionsRepository.findById(soilCompositionId)
            .orElseThrow(() -> new EntityNotFoundException("SoilComposition not found"));
  }

}
