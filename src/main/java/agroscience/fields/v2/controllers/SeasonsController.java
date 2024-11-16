package agroscience.fields.v2.controllers;

import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.mappers.SeasonMapper;
import agroscience.fields.v2.security.TokenUserContext;
import agroscience.fields.v2.services.SeasonsService;
import generated.agroscience.fields.api.SeasonsApi;
import generated.agroscience.fields.api.model.IdDTO;
import generated.agroscience.fields.api.model.SeasonBaseDTO;
import generated.agroscience.fields.api.model.SeasonWithFieldsDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('organization') or hasRole('worker')")
public class SeasonsController implements SeasonsApi, SecurityController {

  private final SeasonsService seasonsService;
  private final SeasonMapper seasonsMapper;

  @Override
  public void changeSeason(UUID seasonId, SeasonBaseDTO seasonBaseDTO) {
    Season updateSeason = seasonsMapper.map(seasonBaseDTO);
    seasonsService.update(seasonId, updateSeason);
  }

  @Override
  public void deleteSeason(UUID seasonId) {
    seasonsService.archive(seasonId);
  }

  @Override
  public List<SeasonWithFieldsDTO> findFullSeasons() {
    TokenUserContext token = token();
    return seasonsService.getAllWithField(token.orgId());
  }

  @Override
  public List<SeasonBaseDTO> findSeasons() {
    TokenUserContext token = token();
    return seasonsService.getAll(token.orgId());
  }

  @Override
  public IdDTO saveSeason(SeasonBaseDTO seasonDTO) {
    Season season = seasonsMapper.map(seasonDTO);
    season.setOrganizationId(token().orgId());
    Season  seasonEntity = seasonsService.save(season);
    return new IdDTO(seasonEntity.getId());
  }

}