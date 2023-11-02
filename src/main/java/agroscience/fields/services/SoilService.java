package agroscience.fields.services;

import agroscience.fields.dao.entities.Crop;
import agroscience.fields.dao.entities.Soil;
import agroscience.fields.dao.repositories.FieldRepository;
import agroscience.fields.dao.repositories.SoilRepository;
import agroscience.fields.exceptions.DuplicateException;
import agroscience.fields.utilities.LocalDateConverting;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoilService {
    private final SoilRepository soilRepository;
    private final FieldRepository fRepository;

    public Soil createSoil(Soil soil, Long fieldId){
        var field = fRepository.findById(fieldId)
                .orElseThrow(() -> new EntityNotFoundException("Не найден поле с id: " + fieldId));
        soil.setField(field);
        try {
            return soilRepository.save(soil);
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateException("Забор почвы с датой " +
                    LocalDateConverting.localDateToString(soil.getSampleDate()) +
                    " уже существует", "sampleDate");
        }
    }

    public void deleteSoilById(Long soilId) {
        Soil soil = soilRepository.findById(soilId).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + soilId));
        soilRepository.delete(soil);
    }
}
