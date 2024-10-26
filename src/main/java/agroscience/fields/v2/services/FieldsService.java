package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.repositories.FieldsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldsService {

  private final FieldsRepository fieldsRepository;

  public FieldV2 save(FieldV2 field) {
    var fieldId = UUID.randomUUID();
    field.setFieldId(fieldId);
    field.getContours().forEach(c -> {
      c.setContourId(UUID.randomUUID());
      c.setField(field);
    });
    return fieldsRepository.save(field); // try ... catch
  }

  public FieldV2 findById(UUID id) {
    return fieldsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Field not found"));
  }

  public List<FieldV2> findAll(UUID seasonId) {
    return fieldsRepository.findAllBySeasonId(seasonId);
  }

}
