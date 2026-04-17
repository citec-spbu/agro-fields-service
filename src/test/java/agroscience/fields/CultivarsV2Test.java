package agroscience.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import agroscience.fields.dto.crop.ResponseCrop;
import agroscience.fields.dto.cultivar.ResponseCultivar;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public class CultivarsV2Test extends AbstractTest {

  @Autowired
  private HttpSteps httpSteps;

  @Test
  public void getCultivarsDictionaryByCropIdTest() {
    ResponseEntity<List<ResponseCrop>> cropsResponse = httpSteps.sendGetRequest(
            "/api/v2/fields-service/crops?page=0&size=10&name=Вин",
            new ParameterizedTypeReference<>() {}
    );

    assertEquals(200, cropsResponse.getStatusCode().value());
    assertNotNull(cropsResponse.getBody());
    assertFalse(cropsResponse.getBody().isEmpty());

    Long cropId = cropsResponse.getBody().get(0).getCropId();

    ResponseEntity<List<ResponseCultivar>> cultivarsResponse = httpSteps.sendGetRequest(
            "/api/v2/fields-service/crops/" + cropId + "/cultivars?page=0&size=10&name=К",
            new ParameterizedTypeReference<>() {}
    );

    assertEquals(200, cultivarsResponse.getStatusCode().value());
    assertNotNull(cultivarsResponse.getBody());
    assertFalse(cultivarsResponse.getBody().isEmpty());
    assertTrue(cultivarsResponse.getBody().stream().allMatch(cultivar -> cultivar.getCropId().equals(cropId)));
  }
}
