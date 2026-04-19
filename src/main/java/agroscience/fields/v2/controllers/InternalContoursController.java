package agroscience.fields.v2.controllers;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.mappers.ContourMapper;
import agroscience.fields.v2.services.ContoursService;
import generated.agroscience.fields.api.model.ContourBaseDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internal/fields-service")
public class InternalContoursController {

  private final ContoursService contoursService;
  private final ContourMapper contourMapper;

  @GetMapping("/fields/{id}/contours")
  @Transactional(readOnly = true)
  public List<ContourBaseDTO> findContoursByField(@PathVariable UUID id) {
    List<Contour> contourList = contoursService.findAllByFieldId(id);
    return contourMapper.map(contourList);
  }
}
