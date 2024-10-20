package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.FieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FieldsContoursService {
  private final FieldsRepository fieldsRepository;
  private final ContoursRepository contoursRepository;

  public UUID save(FieldV2 field) {
    var fieldId = UUID.randomUUID();
    field.setFieldId(fieldId);

    fieldsRepository.save(field); // try ... catch
    return fieldId;
  }
}
