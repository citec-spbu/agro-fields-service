package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.CropRotationV2;
import agroscience.fields.v2.entities.Season;
import agroscience.fields.v2.repositories.SeasonsRepository;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonsService extends DefaultService {

  private final SeasonsRepository seasonsRepository;

  public Season save(Season season) {
    return seasonsRepository.save(season);
  }

  public List<Season> getAll(UUID organizationId) {
    Sort sort = Sort.by(Sort.Direction.DESC, "startDate");
    List<Season> seasons = seasonsRepository.getAllByOrganizationIdAndArchivedIsFalse(organizationId, sort);
    seasons.forEach(season -> {
      season.getFields().forEach(fieldV2 -> {
        fieldV2.getContours().forEach(contour -> {
          List<CropRotationV2> crops = contour.getCropRotations().stream()
                  .sorted(Comparator.comparing(CropRotationV2::getStartDate).reversed())
                  .toList();
          contour.setCropRotations(crops);
        });

      });
    });
    return seasons;
  }

  public void archive(UUID seasonId) {
    Season season = getOrThrow(seasonId, seasonsRepository::findById);
    season.archive();
    seasonsRepository.save(season);
  }

  public void update(UUID seasonId, Season season) {
    Season old = getOrThrow(seasonId, seasonsRepository::findById);
    checkArchived(seasonId, old);
    season.setId(old.getId());
    season.setOrganizationId(old.getOrganizationId());
    season.setFields(old.getFields());
    seasonsRepository.save(season);
  }

}
