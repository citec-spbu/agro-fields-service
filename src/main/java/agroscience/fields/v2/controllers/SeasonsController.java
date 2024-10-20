package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.seasons.RequestSeasons;
import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.services.SeasonsService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v2/fields/seasons")
public class SeasonsController {

  private final SeasonsService seasonsService;
  private final ModelMapper modelMapper;

  @PostMapping
  public String save(@Valid @RequestBody RequestSeasons request) {
    var season = modelMapper.map(request, Season.class);
    return seasonsService.save(season).toString();
  }

  @GetMapping
  public List<Season> getAll(String orgId) {
    return seasonsService.getAll(UUID.fromString(orgId));
  }

}