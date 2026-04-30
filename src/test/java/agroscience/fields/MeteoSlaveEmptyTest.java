package agroscience.fields;

import agroscience.fields.v2.repositories.FieldsRepository;
import agroscience.fields.v2.repositories.SeasonsRepository;
import generated.agroscience.fields.api.model.MeteoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MeteoSlaveEmptyTest extends AbstractTest {

  @Autowired
  private SeasonsRepository seasonsRepository;
  @Autowired
  private FieldsRepository fieldRepository;
  @Autowired
  private HttpSteps httpSteps;

  @BeforeEach
  public void clear() {
    seasonsRepository.deleteAll();
    fieldRepository.deleteAll();
  }

  @Test
  public void getCoordinatesReturnsEmptyListWhenNoFields() {
    String url = "/api/internal/fields-service/fields/all-coordinates";
    ResponseEntity<List<MeteoResponse>> response = httpSteps.sendGetRequest(
            url,
            new ParameterizedTypeReference<>() {}
    );

    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }
}
