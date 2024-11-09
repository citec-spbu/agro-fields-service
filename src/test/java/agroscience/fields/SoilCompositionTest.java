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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.UUID;
import static agroscience.fields.SampleObjectGenerator.createSampleFieldAndContourInside;
import static agroscience.fields.SampleObjectGenerator.createSampleSeason;
import static agroscience.fields.SampleObjectGenerator.createSampleSoilComposition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SoilCompositionTest extends AbstractTest {

  @Autowired
  private SeasonsRepository seasonsRepository;
  @Autowired
  private FieldsRepository fieldRepository;
  @Autowired
  private SoilCompositionsRepository soilCompositionsRepository;
  @Autowired
  private SoilCompositionMapper soilCompositionMapper;
  private static final TestRestTemplate testRestTemplate = new TestRestTemplate();
  private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3Mjk0OTQzNTQsImV4cCI6MTcyOTQ5Nzk1NCwic3ViIjoiYzUzNmQyODEtNTM5MC00YzViLWIxYTMtNjIwNjEzOWZjZTFlIiwicm9sZSI6Im9yZ2FuaXphdGlvbiIsImVtYWlsIjoidGVzdEB0ZXN0LnJ1Iiwib3JnIjoiYzUzNmQyODEtNTM5MC00YzViLWIxYTMtNjIwNjEzOWZjZTFlIn0.0pXuoEo35KiJVYStWFz7cM2UMSbwe19vyZNtIoLDpHs";

  public static <T> ResponseEntity<T> sendPostRequest(Object entityDTO, String url, Class<T> responseType){
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(TOKEN);
    HttpEntity<Object> request = new HttpEntity<>(entityDTO, headers);
    return testRestTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            responseType
    );
  }

  public static <T> ResponseEntity<List<T>> sendGetRequest(String url, ParameterizedTypeReference<List<T>> responseType) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(TOKEN);
    HttpEntity<Object> request = new HttpEntity<>(headers);

    return testRestTemplate.exchange(
            url,
            HttpMethod.GET,
            request,
            responseType
    );
  }

  public static ResponseEntity<Void> sendDeleteRequest(UUID soilCompositionId, String url, String queryParamName) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(TOKEN);
    HttpEntity<Object> request = new HttpEntity<>(headers);
    String urlWithParams = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam(queryParamName, soilCompositionId)
            .toUriString();
    return testRestTemplate.exchange(
            urlWithParams,
            HttpMethod.DELETE,
            request,
            Void.class
    );
  }

  public static ResponseEntity<Void> sendPutRequest(UUID soilCompositionId, Object entityDTO, String url, String queryParamName) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(TOKEN);
    HttpEntity<Object> request = new HttpEntity<>(entityDTO, headers);
    String urlWithParams = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam(queryParamName, soilCompositionId)
            .toUriString();
    return testRestTemplate.exchange(
            urlWithParams,
            HttpMethod.PUT,
            request,
            Void.class
    );
  }

  @BeforeEach
  @AfterEach
  public void clear() {
    fieldRepository.deleteAll();
    soilCompositionsRepository.deleteAll();
    fieldRepository.deleteAll();
  }

  @Test
  public void createSoilCompositionTest() {
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    UUID contourId = field.getContours().get(0).getContourId();
    SoilComposition soilComposition1 = createSampleSoilComposition(field.getContours().get(0));
    SoilComposition soilComposition2 = createSampleSoilComposition(field.getContours().get(0));
    SoilCompositionDTO soilCompositionDTO1 = soilCompositionMapper.map(soilComposition1);
    SoilCompositionDTO soilCompositionDTO2 = soilCompositionMapper.map(soilComposition2);
    // When
    String url = "http://localhost:8080/api/v2/fields-service/contours/" + contourId + "/soil-composition";
    ResponseEntity<IdDTO> response1 = sendPostRequest(soilCompositionDTO1,url, IdDTO.class);
    ResponseEntity<IdDTO> response2 = sendPostRequest(soilCompositionDTO2,url, IdDTO.class);
    // Then
    List<SoilComposition> savedSoilCompositions = soilCompositionsRepository.findAll();
    assertEquals(200, response1.getStatusCode().value());
    assertEquals(200, response2.getStatusCode().value());
    assertNotNull(response1.getBody());
    assertNotNull(response2.getBody());
    assertEquals(IdDTO.class, response1.getBody().getClass());
    assertEquals(IdDTO.class, response2.getBody().getClass());
    assertEquals(2, savedSoilCompositions.size());
    assertEquals(contourId, savedSoilCompositions.get(0).getContour().getContourId());
    assertEquals(contourId, savedSoilCompositions.get(1).getContour().getContourId());
    assertEquals(response1.getBody().getId(), savedSoilCompositions.get(0).getSoilCompositionId());
    assertEquals(response2.getBody().getId(), savedSoilCompositions.get(1).getSoilCompositionId());
    assertNotNull(savedSoilCompositions.get(1).getSampleDate());
    assertEquals("6.5", savedSoilCompositions.get(0).getPh());
    assertEquals("5.2%", savedSoilCompositions.get(1).getOrganicMatter());
  }

  @Test
  public void getSoilCompositionTest(){
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    UUID contourId = field.getContours().get(0).getContourId();
    SoilComposition soilComposition1 = createSampleSoilComposition(field.getContours().get(0));
    SoilComposition soilComposition2 = createSampleSoilComposition(field.getContours().get(0));
    soilCompositionsRepository.save(soilComposition1);
    soilCompositionsRepository.save(soilComposition2);
    // When
    String url = "http://localhost:8080/api/v2/fields-service/contours/" + contourId + "/soil-compositions";
    ResponseEntity<List<SoilCompositionDTO>> response1 = sendGetRequest(url, new ParameterizedTypeReference<>() {});
    ResponseEntity<List<SoilCompositionDTO>> response2 = sendGetRequest(url, new ParameterizedTypeReference<>() {});
    //Then
    assertEquals(200, response1.getStatusCode().value());
    assertEquals(200, response2.getStatusCode().value());
    List<SoilCompositionDTO> responseDto1 = response1.getBody();
    List<SoilCompositionDTO> responseDto2 = response2.getBody();
    assertNotNull(responseDto1);
    assertNotNull(responseDto2);
    assertEquals(soilComposition1.getPh(), responseDto1.get(0).getPh());
    assertEquals(soilComposition1.getSampleDate(), responseDto1.get(0).getSampleDate());
    assertEquals(soilComposition1.getOrganicMatter(), responseDto1.get(0).getOrganicMatter());
    assertEquals(soilComposition1.getMobileP(), responseDto1.get(0).getMobileP());
    assertEquals(soilComposition1.getMobileK(), responseDto1.get(0).getMobileK());
    assertEquals(soilComposition1.getMobileS(), responseDto1.get(0).getMobileS());
    assertEquals(soilComposition1.getNitrateN(), responseDto1.get(0).getNitrateN());
    assertEquals(soilComposition1.getAmmoniumN(), responseDto1.get(0).getAmmoniumN());
    assertEquals(soilComposition1.getHydrolyticAcidity(), responseDto1.get(0).getHydrolyticAcidity());
    assertEquals(soilComposition1.getCaExchange(), responseDto1.get(0).getCaExchange());
    assertEquals(soilComposition1.getMgExchange(), responseDto1.get(0).getMgExchange());
    assertEquals(soilComposition1.getB(), responseDto1.get(0).getB());
    assertEquals(soilComposition1.getCo(), responseDto1.get(0).getCo());
    assertEquals(soilComposition1.getMn(), responseDto1.get(0).getMn());
    assertEquals(soilComposition1.getZn(), responseDto1.get(0).getZn());
    assertEquals(soilComposition2.getPh(), responseDto1.get(0).getPh());
    assertEquals(soilComposition2.getSampleDate(), responseDto2.get(0).getSampleDate());
    assertEquals(soilComposition2.getOrganicMatter(), responseDto2.get(0).getOrganicMatter());
    assertEquals(soilComposition2.getMobileP(), responseDto2.get(0).getMobileP());
    assertEquals(soilComposition2.getMobileK(), responseDto2.get(0).getMobileK());
    assertEquals(soilComposition2.getMobileS(), responseDto2.get(0).getMobileS());
    assertEquals(soilComposition2.getNitrateN(), responseDto2.get(0).getNitrateN());
    assertEquals(soilComposition2.getAmmoniumN(), responseDto2.get(0).getAmmoniumN());
    assertEquals(soilComposition2.getHydrolyticAcidity(), responseDto2.get(0).getHydrolyticAcidity());
    assertEquals(soilComposition2.getCaExchange(), responseDto2.get(0).getCaExchange());
    assertEquals(soilComposition2.getMgExchange(), responseDto2.get(0).getMgExchange());
    assertEquals(soilComposition2.getB(), responseDto2.get(0).getB());
    assertEquals(soilComposition2.getCo(), responseDto2.get(0).getCo());
    assertEquals(soilComposition2.getMn(), responseDto2.get(0).getMn());
    assertEquals(soilComposition2.getZn(), responseDto2.get(0).getZn());
  }

  @Test
  public void deleteSoilCompositionTest(){
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    SoilComposition soilComposition1 = createSampleSoilComposition(field.getContours().get(0));
    SoilComposition soilComposition2 = createSampleSoilComposition(field.getContours().get(0));
    soilCompositionsRepository.save(soilComposition1);
    soilCompositionsRepository.save(soilComposition2);
    //When
    String url = "http://localhost:8080/api/v2/fields-service/soil-composition";
    ResponseEntity<Void> response = sendDeleteRequest(soilComposition1.getSoilCompositionId(),url, "soilCompositionId");
    //Then
    assertEquals(200, response.getStatusCode().value());
    SoilComposition soilComposition = soilCompositionsRepository.findById(soilComposition1.getSoilCompositionId()).orElseThrow();
    assertTrue(soilComposition.isArchived());
    List<SoilComposition> savedSoilCompositions = soilCompositionsRepository.findAllByContourAndArchivedIsFalse(field.getContours().get(0));
    assertEquals(1,savedSoilCompositions.size());
  }

  @Test
  public void putSoilCompositionTest(){
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleFieldAndContourInside(season);
    fieldRepository.save(field);
    SoilComposition soilComposition1 = createSampleSoilComposition(field.getContours().get(0));
    SoilComposition soilComposition2 = createSampleSoilComposition(field.getContours().get(0));
    soilCompositionsRepository.save(soilComposition1);
    soilCompositionsRepository.save(soilComposition2);
    soilComposition1.setCo("KoKOKOkO");
    SoilCompositionDTO soilCompositionDTO = soilCompositionMapper.map(soilComposition1);
    //When
    String url = "http://localhost:8080/api/v2/fields-service/soil-composition";
    ResponseEntity<Void> response = sendPutRequest(soilComposition1.getSoilCompositionId(), soilCompositionDTO, url, "SoilCompositionId");
    //Then
    assertEquals(200, response.getStatusCode().value());
    SoilComposition updatedSoilComposition = soilCompositionsRepository.findById(soilComposition1.getSoilCompositionId()).orElseThrow();
    assertEquals("KoKOKOkO", updatedSoilComposition.getCo());
    assertEquals("1.1 mg/kg", updatedSoilComposition.getZn());
  }

}
