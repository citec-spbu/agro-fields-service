package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.repositories.ContoursRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContoursService {
  private final ContoursRepository contoursRepository;

  public Contour findById(UUID contourId) {
    return contoursRepository.findById(contourId)
            .orElseThrow(() -> new EntityNotFoundException("Contour not found"));
  }

  public UUID save(Contour contour) {
    var contourId = UUID.randomUUID();
    contour.setContourId(contourId);
    contoursRepository.save(contour);
    return contourId;
  }
}
