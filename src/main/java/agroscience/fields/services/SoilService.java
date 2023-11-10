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

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SoilService {
    private final SoilRepository soilRepository;
    private final FieldRepository fRepository;
    private final SoilMapper soilMapper;
    private final JbdcDao dao;

    public Soil createSoil(Long orgId, Soil soil, Long fieldId){
        var field = fRepository.findById(fieldId)
                .orElseThrow(() -> new EntityNotFoundException("Не найден поле с id: " + fieldId));

        if(!Objects.equals(field.getOrganizationId(), orgId)){
            throw new AuthException("Вы не принадлежите организации с id " + field.getOrganizationId());
        }

        soil.setField(field);
        try {
            return soilRepository.save(soil);
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateException("Забор почвы с датой " +
                    LocalDateConverting.localDateToString(soil.getSampleDate()) +
                    " уже существует", "sampleDate");
        }
    }

    public void deleteSoilById(Long soilId, Long orgId) {
        var orgIdBySoilId = dao.getOrgIdBySoilId(soilId);

        if(!Objects.equals(orgId, orgIdBySoilId)){
            throw new AuthException("Вы не принадлежите организации с id " + orgIdBySoilId);
        }
        
        Soil soil = soilRepository.findById(soilId).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + soilId));
        soilRepository.delete(soil);
    }

    public Soil updateSoil(Long orgId, Long soilId, Soil newSoil) {
        var soil = soilRepository.findById(soilId).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + soilId));

        var orgIdBySoilId = dao.getOrgIdBySoilId(soilId);

        if(!Objects.equals(orgId, orgIdBySoilId)){
            throw new AuthException("Вы не принадлежите организации с id " + orgIdBySoilId);
        }

        soilMapper.newSoilToSoil(soil, newSoil);
        try {
            return soilRepository.save(soil);
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateException("Забор почвы с датой " +
                    LocalDateConverting.localDateToString(soil.getSampleDate()) +
                    " уже существует", "sampleDate");
        }
    }
}
