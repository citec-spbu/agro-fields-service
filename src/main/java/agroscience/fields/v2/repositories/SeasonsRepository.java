package agroscience.fields.v2.repositories;

import agroscience.fields.v2.entities.Season;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonsRepository extends AbstractRepository<Season> {

  List<Season> getAllByOrganizationIdAndArchivedIsFalseOrderByStartDateDesc(UUID orgId);

}