package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.SoilComposition;
import agroscience.fields.v2.repositories.ContoursRepository;
import agroscience.fields.v2.repositories.SoilCompositionsRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoilCompositionsService extends DefaultService {

  private final SoilCompositionsRepository soilCompositionsRepository;
  private final ContoursRepository contoursRepository;

  public SoilComposition save(UUID contourId, SoilComposition soilComposition) {
    Contour contour = getOrThrow(contourId, contoursRepository::findById);
    if (soilComposition.getPoint() == null) {
      Coordinate coordinate = contour.getGeom().getCoordinates()[0];
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(coordinate);
      soilComposition.setPoint(point);
    }
    if (!contour.getGeom().contains(soilComposition.getPoint())
            && !contour.getGeom().intersects(soilComposition.getPoint())) {
      throw new IllegalArgumentException("The point does not belong to the contour");
    }
    checkArchived(contourId, contour);
    soilComposition.setContour(contour);
    return soilCompositionsRepository.save(soilComposition);
  }

  public void archive(UUID soilCompositionId) {
    SoilComposition soilComposition = getOrThrow(soilCompositionId, soilCompositionsRepository::findById);
    soilComposition.archive();
    soilCompositionsRepository.save(soilComposition);
  }

  public void updateSoilComposition(UUID soilCompositionId, SoilComposition updatedSoilComposition) {
    SoilComposition oldSoilComposition = getOrThrow(soilCompositionId, soilCompositionsRepository::findById);
    checkArchived(soilCompositionId, oldSoilComposition);
    updatedSoilComposition.setId(oldSoilComposition.getId());
    updatedSoilComposition.setContour(oldSoilComposition.getContour());
    soilCompositionsRepository.save(updatedSoilComposition);
  }

  public List<SoilComposition> getAllSoilCompositions(UUID contourId) {
    Contour contour = getOrThrow(contourId, contoursRepository::findById);
    checkArchived(contourId, contour);
    return soilCompositionsRepository.findAllByContourAndArchivedIsFalse(contour);
  }

}
