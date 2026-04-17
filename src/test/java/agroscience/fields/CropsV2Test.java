package agroscience.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import agroscience.fields.dto.crop.ResponseCrop;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public class CropsV2Test extends AbstractTest {

  @Autowired
  private HttpSteps httpSteps;

  @Test
  public void getCropsDictionaryTest() {
    // Given
    String url = "/api/v2/fields-service/crops?page=0&size=10&name=Пш";

    // When
    ResponseEntity<List<ResponseCrop>> response = httpSteps.sendGetRequest(
            url,
            new ParameterizedTypeReference<>() {}
    );

    // Then
    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().isEmpty());
    assertTrue(response.getBody().stream().allMatch(crop -> crop.getCropName().startsWith("Пш")));
  }
}
