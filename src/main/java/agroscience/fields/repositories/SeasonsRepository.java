package agroscience.fields.repositories;

import agroscience.fields.entities.Season;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonsRepository extends AbstractRepository<Season> {

  List<Season> getAllByOrganizationIdAndArchivedIsFalse(UUID orgId, Sort sort);

}