package agroscience.fields.mappers;

import agroscience.fields.dao.models.FieldAndCurrentCrop;
import agroscience.fields.dao.entities.Field;
import agroscience.fields.dao.models.FieldCRsSoil;
import agroscience.fields.dto.ResponseMeteo;
import agroscience.fields.dto.field.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.mapstruct.Mapper;
import org.locationtech.jts.geom.GeometryFactory;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {FieldMapper.class,CropRotationMapper.class, CropMapper.class, SoilMapper.class})
public interface FieldMapper {
    @Mapping(target = "activityStart", source = "request.activityStart", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "activityEnd", source = "request.activityEnd", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "geom", source = "request.geom", qualifiedByName = "geom")
    Field requestFieldToField(RequestField request, Long organizationId);

    @Mapping(target = "activityStart", source = "request.activityStart", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "activityEnd", source = "request.activityEnd", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "geom", source = "request.geom", qualifiedByName = "geom")
    void requestFieldToField(@MappingTarget Field field, RequestField request, Long organizationId);

    @Mapping(target = "activityStart", source = "dto.field.activityStart", qualifiedByName = "localDateToString")
    @Mapping(target = "activityEnd", source = "field.activityEnd", qualifiedByName = "localDateToString")
    @Mapping(target = "geom", source = "dto.field.geom", qualifiedByName = "geomToResponseGeom")
    @Mapping(target = "id", source = "dto.field.id")
    @Mapping(target = "organizationId", source = "dto.field.organizationId")
    @Mapping(target = "name", source = "dto.field.name")
    @Mapping(target = "squareArea", source = "dto.field.squareArea")
    @Mapping(target = "description", source = "dto.field.description")
    @Mapping(target = "color", source = "dto.field.color")
    @Mapping(target = "crop", source = "dto.cropRotation.crop")
    ResponseFieldPreview fieldToResponseFPreview(FieldAndCurrentCrop dto);

    @Mapping(target = "activityStart", source = "dto.field.activityStart", qualifiedByName = "localDateToString")
    @Mapping(target = "activityEnd", source = "dto.field.activityEnd", qualifiedByName = "localDateToString")
    @Mapping(target = "geom", source = "dto.field.geom", qualifiedByName = "geomToResponseGeom")
    @Mapping(target = "id", source = "dto.field.id")
    @Mapping(target = "organizationId", source = "dto.field.organizationId")
    @Mapping(target = "name", source = "dto.field.name")
    @Mapping(target = "squareArea", source = "dto.field.squareArea")
    @Mapping(target = "description", source = "dto.field.description")
    @Mapping(target = "color", source = "dto.field.color")
    @Mapping(target = "cropRotation", source = "dto.cropRotation")
    ResponseFieldWithCR fieldToResponseField(FieldAndCurrentCrop dto);

    @Mapping(target = "activityStart", source = "dto.field.activityStart", qualifiedByName = "localDateToString")
    @Mapping(target = "activityEnd", source = "dto.field.activityEnd", qualifiedByName = "localDateToString")
    @Mapping(target = "geom", source = "dto.field.geom", qualifiedByName = "geomToResponseGeom")
    @Mapping(target = "id", source = "dto.field.id")
    @Mapping(target = "organizationId", source = "dto.field.organizationId")
    @Mapping(target = "name", source = "dto.field.name")
    @Mapping(target = "squareArea", source = "dto.field.squareArea")
    @Mapping(target = "description", source = "dto.field.description")
    @Mapping(target = "color", source = "dto.field.color")
    @Mapping(target = "cropRotation", source = "dto.cropRotation")
    @Mapping(target = "soil", source = "dto.soil")
    @Mapping(target = "meteoList", source = "meteoList")
    ResponseFullField fieldToResponseFullField(FieldCRsSoil dto, List<ResponseMeteo> meteoList);

    @Named("geomToResponseGeom")
    default GeomDTO geomToResponseGeom(Geometry geom) {
        if (geom != null) {
            Coordinate[] coordinates = geom.getCoordinates();
            List<CoordinatesDTO> responseCoordinatesList = new ArrayList<>();
            for (Coordinate coordinate : coordinates) {
                responseCoordinatesList.add(new CoordinatesDTO(coordinate.x, coordinate.y));
            }
            return new GeomDTO("Polygon", responseCoordinatesList);
        }
        return null;
    }
    @Named("geom")
    default Geometry requestGeomToGeom(GeomDTO request){
        var coordinates = request.getCoordinates();
        GeometryFactory geometryFactory = new GeometryFactory();
        // Преобразовать координаты в массив точек
        Coordinate[] polygonCoordinates = new Coordinate[coordinates.size()];
        for (int i = 0; i < coordinates.size(); i++) {
            polygonCoordinates[i] =
                    new Coordinate(coordinates.get(i).getLongitude(), coordinates.get(i).getLatitude());
        }
        return geometryFactory.createPolygon(polygonCoordinates);
    }
}
