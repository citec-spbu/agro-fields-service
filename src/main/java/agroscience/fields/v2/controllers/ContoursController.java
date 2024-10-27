package agroscience.fields.v2.controllers;

import agroscience.fields.v2.mappers.ContourMapper;
import agroscience.fields.v2.services.ContoursService;
import generated.agroscience.fields.api.ContoursApi;
import generated.agroscience.fields.api.model.ContourDTO;
import generated.agroscience.fields.api.model.IdDTO;
import generated.agroscience.fields.api.model.UpdateContourDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('organization') or hasRole('worker')")
public class ContoursController implements ContoursApi {

  private final ContoursService contourService;
  private final ContourMapper contourMapper;

  @Override
  public void changeContour(UUID contourId, UpdateContourDTO updateContourDTO) {
    // TODO
  }

  @Override
  public void deleteContour(UUID contourId) {
    // TODO
  }

  @Override
  public List<ContourDTO> findContours(UUID fieldId) {
    return null; // TODO
  }

  @Override
  public IdDTO saveContour(UUID fieldId, ContourDTO contourDTO) {
    var contourEntity = contourMapper.map(contourDTO);
    var contourId = contourService.save(fieldId, contourEntity);
    return new IdDTO(contourId);
  }

}