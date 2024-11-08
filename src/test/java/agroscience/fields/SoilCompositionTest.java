package agroscience.fields;

import agroscience.fields.dto.field.CoordinatesDTO;
import agroscience.fields.dto.field.GeomDTO;
import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.entities.SoilComposition;
import agroscience.fields.v2.repositories.FieldsRepository;
import agroscience.fields.v2.repositories.SeasonsRepository;
import agroscience.fields.v2.repositories.SoilCompositionsRepository;
import agroscience.fields.v2.services.SoilCompositionsService;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class SoilCompositionTest extends AbstractTest {

  @Autowired
  private SeasonsRepository seasonsRepository;
  @Autowired
  private FieldsRepository fieldRepository;
  @Autowired
  private SoilCompositionsService soilCompositionsService;
  @Autowired
  private SoilCompositionsRepository soilCompositionsRepository;
  public static Geometry geom() {
    CoordinatesDTO coordinates1 = new CoordinatesDTO(1.0, 2.0);
    CoordinatesDTO coordinates2 = new CoordinatesDTO(3.0, 4.0);
    CoordinatesDTO coordinates3 = new CoordinatesDTO(4.0, 3.0);
    CoordinatesDTO coordinates4 = new CoordinatesDTO(1.0, 2.0);

    List<CoordinatesDTO> coordinatesList = List.of(coordinates1, coordinates2, coordinates3, coordinates4);

    var geomDto = new GeomDTO("Polygon", coordinatesList);

    var coordinates = geomDto.getCoordinates();
    GeometryFactory geometryFactory = new GeometryFactory();
    // Преобразовать координаты в массив точек
    Coordinate[] polygonCoordinates = new Coordinate[coordinates.size()];
    for (int i = 0; i < coordinates.size(); i++) {
      polygonCoordinates[i] =
              new Coordinate(coordinates.get(i).getLongitude(), coordinates.get(i).getLatitude());
    }
    return geometryFactory.createPolygon(polygonCoordinates);
  }

  public static FieldV2 createSampleField(){
    FieldV2 field = new FieldV2();
    field.setFieldId(UUID.randomUUID());
    field.setName("Test Field");
    field.setDescription("Description of the test field");
    Contour contour = new Contour();
    contour.setContourId(UUID.randomUUID());
    contour.setName("Test Contour");
    contour.setSquareArea("1000");
    contour.setColor("FF5733");
    contour.setGeom(geom());
    contour.setField(field);
    field.setContours(List.of(contour));
    return field;
  }
  private static Season createSampleSeason() {
    Season season = new Season();
    season.setSeasonId(UUID.randomUUID());
    season.setName("potato");
    season.setEndDate(LocalDate.now());
    season.setArchived(false);
    season.setStartDate(LocalDate.now());
    season.setOrganizationId(UUID.randomUUID());
    return  season;
  }

  public static SoilComposition createSampleSoilComposition(Contour contour) {
    SoilComposition soilComposition = new SoilComposition();
    soilComposition.setSoilCompositionId(UUID.randomUUID());
    soilComposition.setPh("6.5");
    soilComposition.setSampleDate(LocalDate.now());
    soilComposition.setOrganicMatter("5.2%");
    soilComposition.setMobileP("12.5 mg/kg");
    soilComposition.setMobileK("80 mg/kg");
    soilComposition.setMobileS("15.3 mg/kg");
    soilComposition.setNitrateN("5.1 mg/kg");
    soilComposition.setAmmoniumN("2.3 mg/kg");
    soilComposition.setHydrolyticAcidity("3.5 cmol/kg");
    soilComposition.setCaExchange("15.2 cmol/kg");
    soilComposition.setMgExchange("7.8 cmol/kg");
    soilComposition.setB("0.8 mg/kg");
    soilComposition.setCo("0.05 mg/kg");
    soilComposition.setMn("6.2 mg/kg");
    soilComposition.setZn("1.1 mg/kg");
    soilComposition.setContour(contour);  // Assigning a Contour

    return soilComposition;
  }

  @Test
  @Transactional
  public void createSoilTest() {
    // Given
    Season season = createSampleSeason();
    seasonsRepository.save(season);
    FieldV2 field = createSampleField();
    field.setSeason(season);
    fieldRepository.save(field);
    UUID contourId = field.getContours().get(0).getContourId();
    SoilComposition soilComposition1 = createSampleSoilComposition(field.getContours().get(0));
    SoilComposition soilComposition2 = createSampleSoilComposition(field.getContours().get(0));
    // When
    soilCompositionsService.save(contourId,soilComposition1);
    soilCompositionsService.save(contourId,soilComposition2);
    // Then
    List<SoilComposition> savedSoilCompositions = soilCompositionsRepository.findAll();
    assertNotNull(savedSoilCompositions, "Soil compositions should not be null");
    assertEquals(2, savedSoilCompositions.size(), "There should be exactly two soil compositions for the contour");
    assertTrue(savedSoilCompositions.stream()
                    .anyMatch(soil -> soil.getSoilCompositionId().equals(soilComposition1.getSoilCompositionId())),
            "Soil composition 1 should be in the saved list");
    assertTrue(savedSoilCompositions.stream()
                    .anyMatch(soil -> soil.getSoilCompositionId().equals(soilComposition2.getSoilCompositionId())),
            "Soil composition 2 should be in the saved list");
    assertEquals(contourId, savedSoilCompositions.get(0).getContour().getContourId(),
            "Soil composition 1 should be linked to the correct contour");
    assertEquals(contourId, savedSoilCompositions.get(1).getContour().getContourId(),
            "Soil composition 2 should be linked to the correct contour");
    assertNotNull(savedSoilCompositions.get(0).getSoilCompositionId(), "Soil composition 1 ID should be generated");
    assertNotNull(savedSoilCompositions.get(1).getSoilCompositionId(), "Soil composition 2 ID should be generated");
    assertNotNull(savedSoilCompositions.get(0).getSampleDate(), "Sample date for soil composition 1 should not be null");
    assertNotNull(savedSoilCompositions.get(1).getSampleDate(), "Sample date for soil composition 2 should not be null");
    assertEquals("6.5", savedSoilCompositions.get(0).getPh(), "pH value of soil composition 1 should match the expected value");
    assertEquals("5.2%", savedSoilCompositions.get(1).getOrganicMatter(), "organicMatter value of soil composition 2 should match the expected value");
  }

}
