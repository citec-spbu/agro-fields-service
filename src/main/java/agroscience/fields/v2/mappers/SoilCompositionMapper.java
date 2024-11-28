package agroscience.fields.v2.mappers;

import agroscience.fields.v2.entities.SoilComposition;
import generated.agroscience.fields.api.model.CoordinatesDTO;
import generated.agroscience.fields.api.model.SoilCompositionDTO;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring", uses = {SoilCompositionMapper.class})
public interface SoilCompositionMapper {

  @Mapping(target = "point", source = "request.point", qualifiedByName = "fromPointDTOToGeometry")
  SoilComposition map(SoilCompositionDTO request);

  @Mapping(target = "point", source = "entity.point", qualifiedByName = "fromGeometryToPointDTO")
  SoilCompositionDTO map(SoilComposition entity);

  List<SoilCompositionDTO> map(List<SoilComposition> soilCompositionList);

  @Named("fromPointDTOToGeometry")
  default Geometry mapPoint(CoordinatesDTO point) {
    if (point == null) {
      return null;
    }
    GeometryFactory geometryFactory = new GeometryFactory();
    return geometryFactory.createPoint(new Coordinate(point.getLongitude(), point.getLatitude()));
  }

  @Named("fromGeometryToPointDTO")
  default CoordinatesDTO mapGeometry(Geometry geometry) {
    if (geometry == null) {
      return null;
    }
    Point point = (Point) geometry;
    return new CoordinatesDTO()
            .latitude(point.getY())
            .longitude(point.getX());
  }

}
