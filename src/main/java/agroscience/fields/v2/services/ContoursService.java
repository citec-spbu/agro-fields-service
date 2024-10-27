package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.FieldsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContoursService extends DefaultService {

  private final ContoursRepository contoursRepository;
  private final FieldsRepository fieldsRepository;

  public Contour findById(UUID contourId) {
    return getOrThrow(contoursRepository.findById(contourId), Contour.class);
  }

  public UUID save(UUID fieldId, Contour contour) {
    var field = getOrThrow(fieldsRepository.findById(fieldId), FieldV2.class);
    contour.setField(field);
    var contourId = UUID.randomUUID();
    contour.setContourId(contourId);
    contoursRepository.save(contour);
    return contourId;
  }

}