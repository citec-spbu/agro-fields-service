package agroscience.fields.services;

import agroscience.fields.entities.FieldV2;
import agroscience.fields.entities.Season;
import agroscience.fields.repositories.FieldsRepository;
import agroscience.fields.repositories.SeasonsRepository;
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
    Season season = getOrThrow(seasonId, seasonsRepository::findById);
    checkArchived(seasonId, season);
    field.setSeason(season);
    field.getContours().forEach(c -> c.setField(field));
    return fieldsRepository.save(field);
  }

  public void update(UUID fieldId, FieldV2 updateField) {
    FieldV2 field = getOrThrow(fieldId, fieldsRepository::findById);
    checkArchived(fieldId, field);
    field.setName(updateField.getName());
    field.setDescription(updateField.getDescription());
    fieldsRepository.save(field);
  }

  public List<FieldV2> findAll(UUID seasonId) {
    Season season = getOrThrow(seasonId, seasonsRepository::findById);
    checkArchived(seasonId, season);
    return fieldsRepository.findAllBySeasonIdAndArchivedIsFalse(seasonId);
  }

  public void archive(UUID contourId) {
    FieldV2 fieldV2 = getOrThrow(contourId, fieldsRepository::findById);
    fieldV2.archive();
    fieldsRepository.save(fieldV2);
  }
}
