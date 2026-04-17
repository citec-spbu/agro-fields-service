package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.mappers.ContourMapper;
import agroscience.fields.v2.repositories.FieldsRepository;
import generated.agroscience.fields.api.model.ContourBaseDTO;
import generated.agroscience.fields.api.model.MeteoResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MeteoSlaveService extends DefaultService {

  private final FieldsRepository fieldsRepository;
  private final ContoursService contoursService;
  private final ContourMapper contourMapper;

  @Transactional
  public List<MeteoResponse> getAllFieldCoordinates() {
    List<FieldV2> fieldList = fieldsRepository.findAllByArchivedIsFalse();
    List<MeteoResponse> responseList = new ArrayList<>();
    fieldList.forEach(field -> {
      Coordinate coordinate = field.getContours().get(0).getGeom().getCoordinates()[0];
      responseList.add(new MeteoResponse(coordinate.getX(), coordinate.getY(), field.getId()));
    });
    return responseList;
  }

  @Transactional(readOnly = true)
  public List<ContourBaseDTO> internalFindContoursByField(UUID fieldId) {
    List<Contour> contourList = contoursService.findAllByFieldId(fieldId);
    return contourMapper.map(contourList);
  }

}
