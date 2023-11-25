package agroscience.fields;

import agroscience.fields.dao.entities.Crop;
import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dao.entities.Field;
import agroscience.fields.dao.entities.Soil;
import agroscience.fields.dao.repositories.CropRotationRepository;
import agroscience.fields.dao.repositories.CropsRepository;
import agroscience.fields.dao.repositories.FieldRepository;
import agroscience.fields.dao.repositories.SoilRepository;
import agroscience.fields.dto.ResponseMeteo;
import agroscience.fields.dto.field.CoordinatesDTO;
import agroscience.fields.dto.field.GeomDTO;
import agroscience.fields.dto.field.RequestField;
import agroscience.fields.mappers.FieldMapper;
import agroscience.fields.services.FieldService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@ContextConfiguration(initializers = PostgreTestContainerConfig.Initializer.class)
public class FieldServiceTest {
    @Autowired
    private FieldService fieldService;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private CropsRepository cropsRepository;
    @Autowired
    private SoilRepository soilRepository;
    @Autowired
    private CropRotationRepository CRRepository;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    @AfterEach
    public void clear() {
        fieldRepository.deleteAll();
        soilRepository.deleteAll();
        CRRepository.deleteAll();
    }

    private static Geometry geom() {
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
    private static GeomDTO geomDto(){
        CoordinatesDTO coordinates1 = new CoordinatesDTO(1.0, 2.0);
        CoordinatesDTO coordinates2 = new CoordinatesDTO(3.0, 4.0);
        CoordinatesDTO coordinates3 = new CoordinatesDTO(4.0, 3.0);
        CoordinatesDTO coordinates4 = new CoordinatesDTO(1.0, 2.0);

        List<CoordinatesDTO> coordinatesList = List.of(coordinates1, coordinates2, coordinates3, coordinates4);

        return new GeomDTO("Polygon", coordinatesList);
    }

    @Test
    public void createFieldTest(){
        // Given
        var geom = geom();
        var field = Field.builder().color("FFFFFF").activityStart(LocalDate.now()).activityEnd(LocalDate.now())
                        .name("field").cropRotations(new ArrayList<>()).soils(new ArrayList<>()).squareArea("100")
                        .organizationId(1L).description("").geom(geom).build();
        // When
        var testField = fieldService.createField(field);
        assertNotNull(testField);
        assertNotNull(testField.getField());
        assertEquals(4, testField.getField().getGeom().getCoordinates().length);
        assertNull(testField.getCropRotation().getId());
        assertNotNull(testField.getField().getId());
        assertEquals(testField.getField().getName(), field.getName());
    }

    @Test
    public void getFullFieldTest() {
        // Given
        var geom = geom();
        var field = Field.builder().color("FFFFFF").activityStart(LocalDate.now()).activityEnd(LocalDate.now())
                .name("field").cropRotations(new ArrayList<>()).soils(new ArrayList<>()).squareArea("100")
                .organizationId(1L).description("").geom(geom).build();
        field = fieldRepository.save(field);
        var meteoList = List.of(
                ResponseMeteo.builder().fieldId(field.getId()).day(LocalDate.now()).
                        temperature(1.).pressure(1.).humidity(1.).build(),
                ResponseMeteo.builder().fieldId(field.getId()).day(LocalDate.now()).
                        temperature(1.).pressure(1.).humidity(1.).build()
        );
        ResponseEntity<List<ResponseMeteo>> mockResponse = new ResponseEntity<>(meteoList, HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://meteo-back:8003/api/v1/meteo/" + field.getId()),
                eq(HttpMethod.GET),
                eq(null),
                any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponse);

        // When
        var result = fieldService.getFullField(field.getId(), field.getOrganizationId());

        // Then
        assertNotNull(result);
        assertNotNull(result);
        assertEquals(field.getId(), result.getId());
        assertEquals(meteoList.get(0).getPressure(), result.getMeteoList().get(0).getPressure());
        assertEquals(meteoList.get(0).getTemperature(), result.getMeteoList().get(0).getTemperature());
        assertEquals(meteoList.get(0).getHumidity(), result.getMeteoList().get(0).getHumidity());
        assertEquals(result.getMeteoList().size(), 2);
    }

    @Test
    @Transactional
    public void deleteFieldTest(){
        // Given
        var geom = geom();
        var field = Field.builder().color("FFFFFF").activityStart(LocalDate.now()).activityEnd(LocalDate.now())
                .name("field").cropRotations(new ArrayList<>()).soils(new ArrayList<>()).squareArea("100")
                .organizationId(1L).description("").geom(geom).build();
        var crop = cropsRepository.findCropById(1L);
        var CR = CropRotation.builder().crop(crop).
                startDate(LocalDate.now()).endDate(LocalDate.now()).description("").field(field).build();
        field.getCropRotations().add(CR);
        crop.getCropRotations().add(CR);
        var soil = Soil.builder().sampleDate(LocalDate.now()).field(field).build();
        field.getSoils().add(soil);
        field = fieldRepository.save(field);

        var fieldId = field.getId();
        var soilId = field.getSoils().get(0).getId();
        var CRId = field.getCropRotations().get(0).getId();

        // When
        fieldService.deleteField(field.getId(), field.getOrganizationId());
        fieldRepository.delete(field);
        // Then
        assertFalse(fieldRepository.existsById(fieldId));
        assertFalse(CRRepository.existsById(CRId));
        assertFalse(soilRepository.existsById(soilId));
        assertTrue(cropsRepository.existsById(field.getCropRotations().get(0).getCrop().getId()));
    }

    @Test
    @Transactional
    public void updateFieldTest(){
        // Given
        var geom = geom();
        var field = Field.builder().color("FFFFFF").activityStart(LocalDate.now()).activityEnd(LocalDate.now())
                .name("field").cropRotations(new ArrayList<>()).soils(new ArrayList<>()).squareArea("100")
                .organizationId(1L).description("").geom(geom).build();
        var crop = cropsRepository.findCropById(1L);
        var CR = CropRotation.builder().crop(crop).
                startDate(LocalDate.now()).endDate(LocalDate.now()).description("").field(field).build();
        field.getCropRotations().add(CR);
        crop.getCropRotations().add(CR);
        var soil = Soil.builder().sampleDate(LocalDate.now()).field(field).build();
        field.getSoils().add(soil);
        field = fieldRepository.save(field);
        var newFieldRequest = RequestField.builder().activityStart("10-10-2000").activityEnd("10-10-2001")
                .geom(geomDto()).color("AAAAAA").name("field").description("Hi").squareArea("10").build();

        // When
        var newField = fieldService.updateField(field.getId(), newFieldRequest, field.getOrganizationId());

        // Then
        assertEquals(fieldRepository.findById(newField.getField().getId()).orElseThrow().getSoils().size(), 1);
        assertEquals(fieldRepository.findById(newField.getField().getId()).orElseThrow().getCropRotations().size(), 1);
        assertEquals(fieldRepository.findById(newField.getField().getId()).orElseThrow().getColor(), newFieldRequest.getColor());
        assertEquals(fieldRepository.findAll().size(), 1);
        assertEquals(field.getId(), newField.getField().getId());
    }

}
