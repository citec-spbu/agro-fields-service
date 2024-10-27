package agroscience.fields.v2.repositories;

import agroscience.fields.v2.entities.CropRotationV2;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRotationRepositoryV2 extends JpaRepository<CropRotationV2, UUID> {

}
