package agroscience.fields;


import agroscience.fields.v2.entities.FieldV2;
import java.util.List;
import java.util.UUID;

import agroscience.fields.v2.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldTestRepository extends AbstractRepository<FieldV2> {

  List<FieldV2> findAllBySeasonIdAndArchivedIsFalse(UUID id);

  @Query("SELECT f FROM FieldV2 f JOIN FETCH f.contours")
  List<FieldV2> findAllWithContours();

}