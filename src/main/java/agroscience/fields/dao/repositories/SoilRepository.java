package agroscience.fields.dao.repositories;

import agroscience.fields.dao.entities.Soil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoilRepository extends JpaRepository<Soil, Long> {
}
