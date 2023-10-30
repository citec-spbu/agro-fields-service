package agroscience.fields.services;

import agroscience.fields.dao.entities.Crop;
import agroscience.fields.dao.repositories.CropRotationRepository;
import agroscience.fields.dao.repositories.CropsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropsService {
    private final CropsRepository cropsRepository;
    private final CropRotationRepository CRRepository;

    public Crop createCrop(Crop crop){
        return cropsRepository.save(crop);
    }

    public Crop updateCrop(Long id, Crop newCrop){
        var crop = cropsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + id));
        crop.setName(newCrop.getName());
        return cropsRepository.save(crop);
    }

    public void deleteCropById(Long id) {
        Crop crop = cropsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + id));

        cropsRepository.delete(crop);
    }

    public Crop getCrop(Long cropId) {
        return cropsRepository.findById(cropId).orElseThrow(() -> new EntityNotFoundException("Crop not found with id: " + cropId));
    }
}
