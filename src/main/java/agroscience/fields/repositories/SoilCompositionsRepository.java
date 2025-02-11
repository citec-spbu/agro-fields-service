package agroscience.fields.repositories;

import agroscience.fields.entities.Contour;
import agroscience.fields.entities.SoilComposition;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilCompositionsRepository extends AbstractRepository<SoilComposition> {

  List<SoilComposition> findAllByContourAndArchivedIsFalse(Contour contour, Sort sort);

}