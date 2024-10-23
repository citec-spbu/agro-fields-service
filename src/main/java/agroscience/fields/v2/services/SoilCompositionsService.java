package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.SoilComposition;
import agroscience.fields.v2.repositories.SoilCompositionsRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoilCompositionsService {

  private final SoilCompositionsRepository soilCompositionsRepository;

  public UUID save(SoilComposition soilComposition) {
    var soilCompositionId = UUID.randomUUID();
    soilComposition.setSoilCompositionId(soilCompositionId);
    soilCompositionsRepository.save(soilComposition);
    return soilCompositionId;
  }

}
