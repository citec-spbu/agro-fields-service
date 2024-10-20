package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.FieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldsContoursService {
  private final FieldsRepository fieldsRepository;
  private final ContoursRepository contoursRepository;

  public void save(FieldV2 field, Contour contour) {
    fieldsRepository.save(field);
    contoursRepository.save(contour);
  }
}
