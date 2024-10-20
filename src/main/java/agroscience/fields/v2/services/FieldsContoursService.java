package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Fields;
import agroscience.fields.v2.entities.Seasons;
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

  public List<Seasons> getAll(UUID organization_id) {
    return seasonsRepository.getAllByOrganizationId(organization_id);
  }
}
