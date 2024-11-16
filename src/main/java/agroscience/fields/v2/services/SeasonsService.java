package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.mappers.SeasonMapper;
import agroscience.fields.v2.repositories.SeasonsRepository;
import generated.agroscience.fields.api.model.SeasonBaseDTO;
import generated.agroscience.fields.api.model.SeasonWithFieldsDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonsService extends DefaultService {

  private final SeasonsRepository seasonsRepository;
  private final SeasonMapper seasonMapper;

  public Season save(Season season) {
    return seasonsRepository.save(season);
  }

  public List<SeasonWithFieldsDTO> getAllWithField(UUID organizationId) {
    List<Season> seasons = seasonsRepository.getAllByIdAndArchivedIsFalse(organizationId);
    return seasonMapper.mapField(seasons);
  }

  public List<SeasonBaseDTO> getAll(UUID organizationId) {
    List<Season> seasons = seasonsRepository.getAllByIdAndArchivedIsFalse(organizationId);
    return seasonMapper.map(seasons);
  }

  public void update(UUID seasonId, Season updateSeason) {
    Season season = getOrThrow(seasonId, seasonsRepository::findById);
    checkArchived(seasonId, season);
    updateSeason.setId(seasonId);
    updateSeason.setOrganizationId(season.getOrganizationId());
    updateSeason.setFields(season.getFields());
    seasonsRepository.save(updateSeason);
  }

  public void archive(UUID seasonId) {
    Season season = getOrThrow(seasonId, seasonsRepository::findById);
    season.archive();
    seasonsRepository.save(season);
  }

}
