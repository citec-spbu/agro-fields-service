package agroscience.fields;

import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dao.entities.Field;
import agroscience.fields.dao.entities.Soil;
import agroscience.fields.dao.repositories.CropRotationRepository;
import agroscience.fields.dao.repositories.CropsRepository;
import agroscience.fields.dao.repositories.FieldRepository;
import agroscience.fields.dao.repositories.SoilRepository;
import agroscience.fields.dto.field.CoordinatesDTO;
import agroscience.fields.dto.field.GeomDTO;
import agroscience.fields.services.CropsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(initializers = PostgreTestContainerConfig.Initializer.class)
public class CropsServiceTest {
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private CropRotationRepository CRRepository;
    @Autowired
    private CropsRepository cropsRepository;
    @Autowired
    private SoilRepository soilRepository;
    @Autowired
    private CropsService cropsService;
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
    public void deleteCropTest(){
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

        // When
        cropsService.deleteCropById(crop.getId());

        // Then
        assertFalse(cropsRepository.existsById(cropId));
        assertTrue(fieldRepository.existsById(fieldId));
        assertFalse(CRRepository.existsById(CRId));

    }
}
