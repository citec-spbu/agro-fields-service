package agroscience.fields.services;

import agroscience.fields.dao.entities.Soil;
import agroscience.fields.dao.repositories.FieldRepository;
import agroscience.fields.dao.repositories.JbdcDao;
import agroscience.fields.dao.repositories.SoilRepository;
import agroscience.fields.exceptions.AuthException;
import agroscience.fields.exceptions.DuplicateException;
import agroscience.fields.mappers.SoilMapper;
import agroscience.fields.utilities.LocalDateConverting;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SoilService {
    private final SoilRepository soilRepository;
    private final FieldRepository fRepository;
    private final SoilMapper soilMapper;

    private boolean validateSampleDate(LocalDate date) {
        if (date == null || soilRepository.existsBySampleDate(date)) {
            throw new DuplicateException("Soil with date " + date + " already exists", "sampleDate");
        }
        return true;
    }

    @Transactional
    public Soil createSoil(Long orgId, Soil soil, Long fieldId) {
        var field = fRepository.findById(fieldId)
                .orElseThrow(() -> new EntityNotFoundException("Field with id " + fieldId + " not found"));

        if (!Objects.equals(field.getOrganizationId(), orgId)) {
            throw new AuthException("You do not belong to an organization with id " + orgId);
        }
        validateSampleDate(soil.getSampleDate());
        soil.setField(field);
        return soilRepository.save(soil);
    }

    @Transactional
    public void deleteSoilById(Long soilId, Long orgId) {
        var soil = soilRepository.findById(soilId).orElseThrow(() -> new EntityNotFoundException("Soil with id " + soilId + " not found"));
        var field = soil.getField();
        if (!Objects.equals(orgId, field.getOrganizationId())) {
            throw new AuthException("You do not belong to an organization with id " + orgId);
        }

        field.getSoils().remove(soil);
        soil.setField(null);

        soilRepository.delete(soil);
    }

    @Transactional
    public Soil updateSoil(Long orgId, Long soilId, Soil newSoil) {
        var soil = soilRepository.findById(soilId).orElseThrow(() -> new EntityNotFoundException("Soil with id " + soilId + " not found"));

        var orgIdBySoilId = soil.getField().getOrganizationId();

        if (!Objects.equals(orgId, orgIdBySoilId)) {
            throw new AuthException("You do not belong to an organization with id " + orgId);
        }
        if (soil.getSampleDate() != newSoil.getSampleDate()) {
            validateSampleDate(newSoil.getSampleDate());
        }
        soilMapper.newSoilToSoil(soil, newSoil);
        return soilRepository.save(soil);
    }
}
