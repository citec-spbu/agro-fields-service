package agroscience.fields.services;

import agroscience.fields.dao.entities.Crop;
import agroscience.fields.dao.repositories.CropRotationRepository;
import agroscience.fields.dao.repositories.CropsRepository;
import agroscience.fields.exceptions.DuplicateException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CropsService {
    private final CropsRepository cropsRepository;

    private boolean validateName(String name) {
        if (name == null || name.isBlank() || cropsRepository.existsByName(name)) {
            throw new DuplicateException("Crop with name " + name + " already exists", "name");
        }
        return true;
    }

    @Transactional
    public Crop createCrop(Crop crop) {
        validateName(crop.getName());
        return cropsRepository.save(crop);
    }

    @Transactional
    public Crop updateCrop(Long id, Crop newCrop) {
        var crop = cropsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Field with id " + id + " not found"));
        crop.setName(newCrop.getName());
        if (!Objects.equals(newCrop.getName(), crop.getName())) {
            validateName(newCrop.getName());
        }
        return cropsRepository.save(crop);
    }

    @Transactional
    public void deleteCropById(Long id) {
        Crop crop = cropsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Field with id " + id + " not found"));

        for (var CR : crop.getCropRotations()) {
            CR.getField().getCropRotations().remove(CR);
            CR.setField(null);
        }

        cropsRepository.delete(crop);
    }

    public Crop getCrop(Long cropId) {
        return cropsRepository.findById(cropId).orElseThrow(() -> new EntityNotFoundException("Field with id " + cropId + " not found"));
    }

    public List<Crop> getCrop(String name, int page, int size) {
        return cropsRepository.getAllByNameStartingWith(
                name, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))
        );
    }
}
