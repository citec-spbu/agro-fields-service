package agroscience.fields.v2.controllers;

import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.mappers.SeasonMapper;
import agroscience.fields.v2.services.SeasonsService;
import generated.agroscience.fields.api.SeasonApi;
import generated.agroscience.fields.api.model.IdDTO;
import generated.agroscience.fields.api.model.SeasonDTO;
import generated.agroscience.fields.api.model.SeasonWithFieldsDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('organization') or hasRole('worker')")
public class SeasonsController implements SeasonApi, SecurityController {

  private final SeasonsService seasonsService;
  private final SeasonMapper seasonsMapper;

  @Override
  public void deleteSeason(UUID seasonId) {
    // TODO
  }

  @Override
  public List<SeasonWithFieldsDTO> findFullSeasons() {
    return null; // TODO
  }

  @Override
  public List<SeasonDTO> findSeasons() {
    return seasonsMapper.map(seasonsService.getAll(token().orgId()));
  }

  @Override
  public IdDTO saveSeason(SeasonDTO seasonDTO) {
    Season season = seasonsMapper.map(seasonDTO);
    season.setOrganizationId(token().orgId());
    var seasonEntity = seasonsService.save(season);
    return new IdDTO(seasonEntity.getSeasonId());
  }

}