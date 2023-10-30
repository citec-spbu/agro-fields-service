package agroscience.fields.dao.repositories;

import agroscience.fields.dao.entities.CropRotation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CropRotationRepository extends JpaRepository<CropRotation, Long> {

    Slice<CropRotation> findAllByFieldId(Long fieldId, Pageable pageable);
    @Query("SELECT c FROM CropRotation c")
    Slice<CropRotation> findAllAsSliceByOrgId(Long orgId, Pageable pageable);
@Query("SELECT cr FROM CropRotation cr " +
        "WHERE cr.field.id = :fieldId " +
        "AND cr.startDate = (SELECT MAX(cr2.startDate) FROM CropRotation cr2 WHERE cr2.field.id = :fieldId)")
CropRotation findLatestCR(Long fieldId);
}
