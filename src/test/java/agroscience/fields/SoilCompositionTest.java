package agroscience.fields;

import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.entities.SoilComposition;
import agroscience.fields.v2.mappers.SoilCompositionMapper;
import agroscience.fields.v2.repositories.FieldsRepository;
import agroscience.fields.v2.repositories.SeasonsRepository;
import agroscience.fields.v2.repositories.SoilCompositionsRepository;
import generated.agroscience.fields.api.model.IdDTO;
import generated.agroscience.fields.api.model.SoilCompositionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;
import static agroscience.fields.SampleObjectGenerator.createSampleFieldAndContourInside;
import static agroscience.fields.SampleObjectGenerator.createSampleSeason;
import static agroscience.fields.SampleObjectGenerator.createSampleSoilComposition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SoilCompositionTest extends AbstractTest {

  @Autowired
  private SeasonsRepository seasonsRepository;
  @Autowired
  private FieldsRepository fieldRepository;
  @Autowired
  private SoilCompositionsRepository soilCompositionsRepository;
  @Autowired
  private SoilCompositionMapper soilCompositionMapper;
  @Autowired
  private HttpSteps httpSteps;

  @BeforeEach
  public void clear() {
    fieldRepository.deleteAll();
    soilCompositionsRepository.deleteAll();
    seasonsRepository.deleteAll();
  }

  @Test
  public void createSoilCompositionTest() {
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    UUID contourId = field.getContours().get(0).getId();
    SoilComposition soilComposition = createSampleSoilComposition(field.getContours().get(0));
    // When
    SoilCompositionDTO soilCompositionDTO = soilCompositionMapper.map(soilComposition);
    String url = "/api/v2/fields-service/contours/" + contourId + "/soil-composition";
    ResponseEntity<IdDTO> response = httpSteps.sendPostRequest(soilCompositionDTO,url, IdDTO.class);
    // Then
    List<SoilComposition> savedSoilCompositions = soilCompositionsRepository.findAll();
    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertEquals(IdDTO.class, response.getBody().getClass());
    assertEquals(new IdDTO(savedSoilCompositions.get(0).getId()), response.getBody());
    assertEquals(1, savedSoilCompositions.size());
    assertEquals(soilComposition,savedSoilCompositions.get(0));
  }

  @Test
  public void getSoilCompositionsTest(){
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    UUID contourId = field.getContours().get(0).getId();
    SoilComposition soilComposition1 = createSampleSoilComposition(field.getContours().get(0));
    SoilComposition soilComposition2 = createSampleSoilComposition(field.getContours().get(0));
    soilCompositionsRepository.save(soilComposition1);
    soilCompositionsRepository.save(soilComposition2);
    // When
    String url = "/api/v2/fields-service/contours/" + contourId + "/soil-compositions";
    ResponseEntity<List<SoilCompositionDTO>> response = httpSteps.sendGetRequest(url, new ParameterizedTypeReference<>() {});
    //Then
    assertEquals(200, response.getStatusCode().value());
    List<SoilCompositionDTO> responseDto = response.getBody();
    assertNotNull(responseDto);
    assertEquals(2,responseDto.size());
    assertEquals(responseDto.get(0),soilCompositionMapper.map(soilComposition1));
    assertEquals(responseDto.get(1),soilCompositionMapper.map(soilComposition2));
  }

  @Test
  public void deleteSoilCompositionTest(){
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    SoilComposition soilComposition = createSampleSoilComposition(field.getContours().get(0));
    soilCompositionsRepository.save(soilComposition);
    //When
    String url = "/api/v2/fields-service/soil-composition";
    ResponseEntity<Void> response = httpSteps.sendDeleteRequest(soilComposition.getId(),url, "id");
    //Then
    assertEquals(200, response.getStatusCode().value());
    List<SoilComposition> savedSoilCompositions = soilCompositionsRepository.findAll();
    assertTrue(savedSoilCompositions.get(0).isArchived());
    List<SoilComposition> savedSoilCompositionsAndNotArchived = soilCompositionsRepository.findAllByContourAndArchivedIsFalse(field.getContours().get(0));
    assertEquals(0,savedSoilCompositionsAndNotArchived.size());
  }

  @Test
  public void putSoilCompositionTest(){
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    SoilComposition soilComposition = createSampleSoilComposition(field.getContours().get(0));
    soilCompositionsRepository.save(soilComposition);
    //When
    soilComposition.setCo("KoKOKOkO");
    SoilCompositionDTO soilCompositionDTO = soilCompositionMapper.map(soilComposition);
    String url = "/api/v2/fields-service/soil-composition";
    ResponseEntity<Void> response = httpSteps.sendPutRequest(soilComposition.getId(), soilCompositionDTO, url, "id");
    //Then
    assertEquals(200, response.getStatusCode().value());
    List<SoilComposition> soilCompositions = soilCompositionsRepository.findAll();
    assertEquals(soilCompositionDTO, soilCompositionMapper.map(soilCompositions.get(0)));
    assertEquals(soilComposition, soilCompositions.get(0));
  }

}
