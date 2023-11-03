package agroscience.fields.dao.repositories;

import agroscience.fields.dao.models.FieldAndCurrentCrop;
import agroscience.fields.dao.entities.Field;
import agroscience.fields.dao.models.FieldCRsSoil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldRepository extends JpaRepository<Field, Long> {
    public Slice<Field> findFieldsByOrganizationId(Long organizationId, Pageable pageable);

    @Query("SELECT f as field, cr as cropRotation " +
            "FROM Field f " +
            "LEFT JOIN f.cropRotations cr " +
            "WHERE (cr.startDate = (SELECT MAX(cr2.startDate) " +
            "                      FROM CropRotation cr2 " +
            "                      WHERE cr2.field = f) " +
            "       OR cr.startDate IS NULL) " +
            "AND f.organizationId = :orgId")
    Slice<FieldAndCurrentCrop> fieldsWithLatestCrops(@Param("orgId") Long orgId, Pageable pageable);

    @Query("SELECT f as field, cr as cropRotation " +
            "FROM Field f " +
            "LEFT JOIN f.cropRotations cr " +
            "WHERE (cr.startDate = (SELECT MAX(cr2.startDate) " +
            "                      FROM CropRotation cr2 " +
            "                      WHERE cr2.field = f) " +
            "       OR cr.startDate IS NULL) " +
            "AND f.id = :fieldId")
    FieldAndCurrentCrop fieldWithLatestCrop(@Param("fieldId") Long fieldId);

    @Query("SELECT f as field, cr as cropRotation, s as soil, c as crop " +
            "FROM Field f " +
            "LEFT JOIN f.cropRotations cr " +
            "LEFT JOIN f.soils s " +
            "LEFT JOIN cr.crop c " +
            "WHERE f.id = :fieldId " +
            "ORDER BY cr.startDate DESC, s.sampleDate DESC " +
            "LIMIT 1")
    FieldCRsSoil getFullField(@Param("fieldId") Long fieldId);
}
