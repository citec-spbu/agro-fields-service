package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contours;
import agroscience.fields.v2.entities.Fields;
import agroscience.fields.v2.entities.Seasons;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.FieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FieldsContoursService {
  private final FieldsRepository fieldsRepository;
  private final ContoursRepository contoursRepository;

  public void save(Fields field, Contours contour) {
    fieldsRepository.save(field);
    contoursRepository.save(contour);
  }
}
