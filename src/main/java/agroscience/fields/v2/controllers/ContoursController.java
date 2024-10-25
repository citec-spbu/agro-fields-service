package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.ContourDTO;
import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.mappers.ContourMapper;
import agroscience.fields.v2.services.ContoursService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "api/v2/fields/contours")
@RequiredArgsConstructor
public class ContoursController {

  private final ContoursService contourService;
  private final ContourMapper contourMapper;

  @GetMapping
  @PreAuthorize("hasRole('organization') or hasRole('worker')")
  public ContourDTO get(@Valid @RequestParam String contourId) {
    return contourMapper.map(contourService.findById(UUID.fromString(contourId)));
  }

}
