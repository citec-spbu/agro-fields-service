package agroscience.fields.dao.repositories;

import agroscience.fields.dao.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CropsRepository extends JpaRepository<Crop, Long> {
//    @Query("SELECT c FROM Crop c " +
//            "WHERE c.id = (SELECT cr.crop.id FROM CropRotation cr " +
//            "WHERE cr.field.id = :fieldId " +
//            "ORDER BY cr.startDate DESC " +
//            "LIMIT 1)")
//    Crop findLatestCrop(Long fieldId);

    Crop findCropById(Long cropId);


}
