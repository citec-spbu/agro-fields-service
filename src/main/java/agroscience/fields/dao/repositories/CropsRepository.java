package agroscience.fields.dao.repositories;

import agroscience.fields.dao.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CropsRepository extends JpaRepository<Crop, Long> {
    Crop findCropById(Long cropId);

    boolean existsByName(String name);
}
