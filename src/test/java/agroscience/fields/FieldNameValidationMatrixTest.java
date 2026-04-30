package agroscience.fields;

import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.mappers.FieldMapperV2;
import agroscience.fields.v2.repositories.FieldsRepository;
import agroscience.fields.v2.repositories.SeasonsRepository;
import generated.agroscience.fields.api.model.ExceptionBody;
import generated.agroscience.fields.api.model.FieldBaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static agroscience.fields.SampleObjectGenerator.createSampleFieldAndContourInside;
import static agroscience.fields.SampleObjectGenerator.createSampleSeason;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldNameValidationMatrixTest extends AbstractTest {

  @Autowired
  private SeasonsRepository seasonsRepository;
  @Autowired
  private FieldsRepository fieldsRepository;
  @Autowired
  private FieldMapperV2 fieldMapperV2;
  @Autowired
  private HttpSteps httpSteps;

  @BeforeEach
  public void clear() {
    fieldsRepository.deleteAll();
    seasonsRepository.deleteAll();
  }

  @ParameterizedTest
  @ValueSource(ints = {
          51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
          61, 62, 63, 64, 65, 66, 67, 68, 69, 70
  })
  public void createFieldRejectsLongNameMatrix(int length) {
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    field.setName("A".repeat(length));

    FieldBaseDTO fieldBaseDTO = fieldMapperV2.map(field);
    String url = "/api/v2/fields-service/seasons/" + season.getId() + "/field";
    ResponseEntity<ExceptionBody> response = httpSteps.sendPostRequest(fieldBaseDTO, url, ExceptionBody.class);

    assertEquals(400, response.getStatusCode().value());
    assertEquals(0, fieldsRepository.count());
  }
}
