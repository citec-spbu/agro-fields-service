package agroscience.fields;

import agroscience.fields.v2.entities.CropRotationV2;
import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.mappers.CropRotationMapperV2;
import agroscience.fields.v2.repositories.CropRotationRepositoryV2;
import agroscience.fields.v2.repositories.FieldsRepository;
import agroscience.fields.v2.repositories.SeasonsRepository;
import generated.agroscience.fields.api.model.CropRotationDTO;
import generated.agroscience.fields.api.model.IdDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;
import static agroscience.fields.SampleObjectGenerator.createSampleCropRotation;
import static agroscience.fields.SampleObjectGenerator.createSampleFieldAndContourInside;
import static agroscience.fields.SampleObjectGenerator.createSampleSeason;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CropRotationV2Test extends AbstractTest {

  @Autowired
  private SeasonsRepository seasonsRepository;
  @Autowired
  private FieldsRepository fieldRepository;
  @Autowired
  private CropRotationRepositoryV2 cropRotationRepository;
  @Autowired
  private CropRotationMapperV2 cropRotationMapper;
  @Autowired
  private HttpSteps httpSteps;

  @BeforeEach
  public void clear() {
    cropRotationRepository.deleteAll();
    fieldRepository.deleteAll();
    seasonsRepository.deleteAll();
  }

  @Test
  public void createCropRotationTest() {
    //Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    UUID contourId = field.getContours().get(0).getId();
    CropRotationV2 cropRotation = createSampleCropRotation(field.getContours().get(0));
    cropRotationRepository.save(cropRotation);
    //When
    CropRotationDTO cropRotationDTO = cropRotationMapper.map(cropRotation);
    String url = "/api/v2/fields-service/contours/" + contourId + "/crop-rotation";
    ResponseEntity<IdDTO> response = httpSteps.sendPostRequest(cropRotationDTO, url, IdDTO.class);
    //Then
    List<CropRotationV2> savedCropRotations = cropRotationRepository.findAll();
    cropRotation.setId(savedCropRotations.get(0).getId());
    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertEquals(IdDTO.class, response.getBody().getClass());
    assertEquals(new IdDTO(savedCropRotations.get(0).getId()), response.getBody());
    assertEquals(1, savedCropRotations.size());
    assertEquals(cropRotation.getId(), savedCropRotations.get(0).getId());
  }

  @Test
  public void getCropRotationTest() {
    //Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    UUID contourId = field.getContours().get(0).getId();
    CropRotationV2 cropRotation1 = createSampleCropRotation(field.getContours().get(0));
    cropRotationRepository.save(cropRotation1);
    CropRotationV2 cropRotation2 = createSampleCropRotation(field.getContours().get(0));
    cropRotationRepository.save(cropRotation2);
    //When
    String url = "/api/v2/fields-service/contours/" + contourId + "/crop-rotations";
    ResponseEntity<List<CropRotationDTO>> response = httpSteps.sendGetRequest(url, new ParameterizedTypeReference<>() {});
    //Then
    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().size());
    assertEquals(response.getBody().get(0), cropRotationMapper.map(cropRotation1));
    assertEquals(response.getBody().get(1), cropRotationMapper.map(cropRotation2));
  }

  @Test
  public void deleteCropRotationTest() {
    //Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    CropRotationV2 cropRotation = createSampleCropRotation(field.getContours().get(0));
    cropRotationRepository.save(cropRotation);
    //When
    String url = "/api/v2/fields-service/crop-rotation";
    ResponseEntity<Void> response = httpSteps.sendDeleteRequest(cropRotation.getId(), url, "id");
    //Then
    assertEquals(200, response.getStatusCode().value());
    assertTrue(cropRotationRepository.findAll().get(0).isArchived());
    assertEquals(0, cropRotationRepository.getAllByContourIdAndArchivedIsFalse(cropRotation.getContour().getId()).size());
  }

  @Test
  public void putCropRotationTest() {
    //Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    CropRotationV2 cropRotation = createSampleCropRotation(field.getContours().get(0));
    cropRotationRepository.save(cropRotation);
    //When
    cropRotation.setDescription("Test");
    CropRotationDTO cropRotationDTO = cropRotationMapper.map(cropRotation);
    String url = "/api/v2/fields-service/crop-rotation";
    ResponseEntity<Void> response = httpSteps.sendPutRequest(cropRotation.getId(), cropRotationDTO, url, "id");
    //Then
    assertEquals(200, response.getStatusCode().value());
    assertEquals(cropRotationDTO, cropRotationMapper.map(cropRotationRepository.findAll().get(0)));
    assertEquals(cropRotation, cropRotationRepository.findAll().get(0));
  }

}
