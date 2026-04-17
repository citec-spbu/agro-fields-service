package agroscience.fields.dao.repositories;

import agroscience.fields.dao.entities.Cultivar;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultivarsRepository extends JpaRepository<Cultivar, Long> {

  List<Cultivar> findAllByCropCropIdAndCultivarNameIgnoreCaseStartingWith(
          Long cropId, String cultivarName, PageRequest pageRequest
  );
}
