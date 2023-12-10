package agroscience.fields;

import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dao.entities.Field;
import agroscience.fields.dao.entities.Soil;
import agroscience.fields.dao.repositories.*;
import agroscience.fields.dto.field.CoordinatesDTO;
import agroscience.fields.dto.field.GeomDTO;
import agroscience.fields.exceptions.DuplicateException;
import agroscience.fields.services.SoilService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SoilServiceTest extends AbstractTest{
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private CropRotationRepository CRRepository;
    @Autowired
    private CropsRepository cropsRepository;
    @Autowired
    private SoilRepository soilRepository;
    @Autowired
    private SoilService soilService;
    @Autowired
    private JbdcDao dao;

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

    @Test
    @Transactional
    public void deleteSoilTest(){
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
        crop = field.getCropRotations().get(0).getCrop();

        var cropId = crop.getId();
        var fieldId = field.getId();
        var CRId = field.getCropRotations().get(0).getId();
        var soilId = field.getSoils().get(0).getId();

        // When
        soilService.deleteSoilById(soilId, field.getOrganizationId());

        // Then
        assertFalse(soilRepository.existsById(soilId));
        assertTrue(cropsRepository.existsById(cropId));
        assertTrue(fieldRepository.existsById(fieldId));
        assertTrue(CRRepository.existsById(CRId));

    }

    @Test
    @Transactional
    public void createSoilTest(){
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

        var newSoil = Soil.builder().sampleDate(LocalDate.parse("2000-10-10")).build();
        var newSoil2 = Soil.builder().sampleDate(LocalDate.parse("2000-10-10")).build();
        var newSoil3 = Soil.builder().sampleDate(LocalDate.parse("2001-10-10")).build();
        // When
        var orgId = field.getOrganizationId();
        var fieldId = field.getId();
        newSoil = soilService.createSoil(orgId,newSoil, fieldId);
        assertThrows(DuplicateException.class, ()->soilService.createSoil(orgId, newSoil2, fieldId));
        newSoil3 = soilService.createSoil(orgId,newSoil3, fieldId);

        // Then
        assertEquals(soilRepository.findAll().size(), 3);
        assertEquals(newSoil.getId() + 1, newSoil3.getId());
        assertEquals(soilRepository.findById(newSoil.getId()).orElseThrow().getField().getId(), field.getId());
        assertEquals(newSoil.getSampleDate(), LocalDate.parse("2000-10-10"));
    }

    @Test
    @Transactional
    public void updateSoilTest(){
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

        var newSoil = Soil.builder().sampleDate(LocalDate.parse("2000-10-10")).build();
        // When
        newSoil = soilService.updateSoil(field.getOrganizationId(), field.getSoils().get(0).getId(), newSoil);

        // Then
        assertEquals(soilRepository.findAll().size(), 1);
        assertEquals(soilRepository.findById(newSoil.getId()).orElseThrow().getField().getId(), field.getId());
        assertEquals(field.getSoils().size(), 1);
        assertEquals(newSoil.getSampleDate(), LocalDate.parse("2000-10-10"));
    }

}
