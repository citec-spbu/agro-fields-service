package agroscience.fields.dao.repositories;

import agroscience.fields.dao.models.FandCRandC;
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
    @Query("SELECT f as field, cr as cropRotation, c as crop " +
            "FROM CropRotation cr " +
            "LEFT JOIN cr.field f " +
            "LEFT JOIN cr.crop c " +
            "WHERE cr.id = :id" )
    FandCRandC findCropRotationById(Long id);

    @Query("SELECT f as field, cr as cropRotation, c as crop " +
            "FROM CropRotation cr " +
            "LEFT JOIN cr.field f " +
            "LEFT JOIN cr.crop c " +
            "WHERE f.organizationId = :orgId " +
            "ORDER BY cr.startDate DESC")
    Slice<FandCRandC> findAllByOrgId(Long orgId, Pageable pageable);
}
