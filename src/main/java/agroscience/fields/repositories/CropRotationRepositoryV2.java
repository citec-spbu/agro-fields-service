package agroscience.fields.repositories;

import agroscience.fields.entities.CropRotationV2;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;

public interface CropRotationRepositoryV2 extends AbstractRepository<CropRotationV2> {

  List<CropRotationV2> getAllByContourIdAndArchivedIsFalse(UUID contourId, Sort sort);

}