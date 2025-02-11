package agroscience.fields.repositories;

import agroscience.fields.entities.Contour;
import agroscience.fields.entities.FieldV2;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ContoursRepository extends AbstractRepository<Contour> {

  List<Contour> findAllByFieldAndArchivedIsFalse(FieldV2 fieldV2);

}