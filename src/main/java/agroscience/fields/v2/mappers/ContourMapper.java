package agroscience.fields.v2.mappers;

import agroscience.fields.exceptions.WrongCoordinatesException;
import agroscience.fields.v2.entities.Contour;
import generated.agroscience.fields.api.model.ContourDTO;
import generated.agroscience.fields.api.model.ContourSummaryDTO;
import generated.agroscience.fields.api.model.CoordinatesDTO;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ContourMapper {

  @Mapping(target = "geom", source = "request.coordinates", qualifiedByName = "toGeom")
  Contour map(ContourDTO request);

  @Mapping(target = "coordinates", source = "contour.geom", qualifiedByName = "toCoordinates")
  ContourDTO map(Contour contour);

  @Mapping(target = "coordinates", source = "contour.geom", qualifiedByName = "toCoordinates")
  ContourSummaryDTO arMap(Contour contour);

  @Named("toGeom")
  default Geometry mapGeom(List<CoordinatesDTO> coordinates) {
    GeometryFactory geometryFactory = new GeometryFactory();
    // Преобразовать координаты в массив точек
    Coordinate[] polygonCoordinates = new Coordinate[coordinates.size()];
    for (int i = 0; i < coordinates.size(); i++) {
      polygonCoordinates[i] =
              new Coordinate(coordinates.get(i).getLongitude(), coordinates.get(i).getLatitude());
    }
    try {
      return geometryFactory.createPolygon(polygonCoordinates);
    } catch (IllegalArgumentException e) {
      throw new WrongCoordinatesException("Points of polygon do not form a closed linestring");
    }
  }

  @Named("toCoordinates")
  default List<CoordinatesDTO> mapGeom(Geometry geom) {
    if (geom != null) {
      Coordinate[] coordinates = geom.getCoordinates();
      List<CoordinatesDTO> dtoCoordinates = new LinkedList<>();
      for (Coordinate coordinate : coordinates) {
        dtoCoordinates.add(new CoordinatesDTO(coordinate.x, coordinate.y));
      }
      return dtoCoordinates;
    }
    return Collections.emptyList();
  }

}
