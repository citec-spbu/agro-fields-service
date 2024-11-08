package agroscience.fields.v2.repositories;

import agroscience.fields.v2.entities.Contour;
import agroscience.fields.v2.entities.FieldV2;
import agroscience.fields.v2.entities.SoilComposition;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilCompositionsRepository extends JpaRepository<SoilComposition, UUID> {

  List<SoilComposition> findAllByContourAndArchivedIsFalse(Contour contour);

}
