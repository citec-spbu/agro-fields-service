package agroscience.fields.dao.repositories;

import agroscience.fields.dao.entities.Crop;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CropsRepository extends JpaRepository<Crop, Long> {
    Crop findCropById(Long cropId);

    boolean existsByName(String name);

    List<Crop> getAllByNameStartingWith(String name, PageRequest of);
}
