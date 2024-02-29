package agroscience.fields.dao.repositories;

import agroscience.fields.dao.entities.Soil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SoilRepository extends JpaRepository<Soil, Long> {
    boolean existsBySoilSampleDate(LocalDate date);
}
