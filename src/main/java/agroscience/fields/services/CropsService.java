package agroscience.fields.services;

import agroscience.fields.dao.entities.Crop;
import agroscience.fields.dao.repositories.CropRotationRepository;
import agroscience.fields.dao.repositories.CropsRepository;
import agroscience.fields.exceptions.DuplicateException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropsService {
    private final CropsRepository cropsRepository;
    private final CropRotationRepository CRRepository;

    public Crop createCrop(Crop crop){
        try {
            return cropsRepository.save(crop);
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateException("Культура с именем " + crop.getName() + " уже существует", "name");
        }
    }

    public Crop updateCrop(Long id, Crop newCrop){
        var crop = cropsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + id));
        crop.setName(newCrop.getName());
        try {
            return cropsRepository.save(crop);
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateException("Культура с именем " + crop.getName() + " уже существует", "name");
        }
    }

    public void deleteCropById(Long id) {
        Crop crop = cropsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + id));

        for(var CR : crop.getCropRotations()){
            CR.getField().getCropRotations().remove(CR);
            CR.setField(null);
        }

        cropsRepository.delete(crop);
    }

    public Crop getCrop(Long cropId) {
        return cropsRepository.findById(cropId).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + cropId));
    }
}
