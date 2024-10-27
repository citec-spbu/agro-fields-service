package agroscience.fields.v2.repositories;

import agroscience.fields.v2.entities.FieldV2;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldsRepository extends JpaRepository<FieldV2, UUID> {

  List<FieldV2> findAllBySeason_SeasonId(UUID id);

}