package agroscience.fields.v2.repositories;

import agroscience.fields.v2.entities.Contours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContoursRepository extends JpaRepository<Contours, UUID> {

}
