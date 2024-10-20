package agroscience.fields.v2.repositories;

import agroscience.fields.v2.entities.Seasons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeasonsRepository extends JpaRepository<Seasons, UUID> {
    List<Seasons> getAllByOrganizationId(UUID organizationId);
}
