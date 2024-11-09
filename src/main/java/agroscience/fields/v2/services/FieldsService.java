package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.repositories.FieldsRepository;
import agroscience.fields.v2.repositories.SeasonsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldsService extends DefaultService {

  private final FieldsRepository fieldsRepository;
  private final SeasonsRepository seasonsRepository;

  public FieldV2 save(UUID seasonId, FieldV2 field) {
    var season = getOrThrow(seasonId, seasonsRepository::findById);
    field.setSeason(season);
    field.getContours().forEach(c -> c.setField(field));
    return fieldsRepository.save(field);
  }

  public FieldV2 findById(UUID id) {
    return fieldsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Field not found"));
  }

  public List<FieldV2> findAll(UUID seasonId) {
    return fieldsRepository.findAllBySeason_Id(seasonId);
  }

}
