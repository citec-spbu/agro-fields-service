package agroscience.fields.repositories;

import agroscience.fields.entities.AbstractEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {

  List<T> getAllByIdAndArchivedIsFalse(UUID id);


}