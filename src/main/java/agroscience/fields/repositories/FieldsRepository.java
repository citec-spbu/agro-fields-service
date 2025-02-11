package agroscience.fields.repositories;

import agroscience.fields.entities.FieldV2;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldsRepository extends AbstractRepository<FieldV2> {

  List<FieldV2> findAllBySeasonIdAndArchivedIsFalse(UUID id);

  List<FieldV2> findAllByArchivedIsFalse();

}