package agroscience.fields.services;

import agroscience.fields.dao.models.FandCRandC;
import agroscience.fields.dao.models.FandCRandCImpl;
import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dao.repositories.CropRotationRepository;
import agroscience.fields.dao.repositories.CropsRepository;
import agroscience.fields.dao.repositories.FieldRepository;
import agroscience.fields.dto.croprotation.ResponseCRWithField;
import agroscience.fields.mappers.CropRotationMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropRotationsService {
    private final CropRotationRepository CRRepository;
    private final CropsRepository cropsRepository;
    private final CropRotationMapper CRMapper;
    private final FieldRepository fRepository;

    public List<CropRotation> getAllByFieldId(Long fieldId, PageRequest request) {
        return CRRepository.findAllByFieldId(fieldId, request).toList();
    }

    public List<FandCRandC> getAll(Long orgId, PageRequest request) {
        return CRRepository.findAllByOrgId(orgId, request).toList();
//        return DifficultMapping.listResponseCRWithFieldList(cropRotations.get, fieldNames);
//        return null;
    }

    public ResponseCRWithField createCR(CropRotation cropRotation, Long cropId, Long fieldId){
        var crop = cropsRepository.findById(cropId)
                .orElseThrow(() -> new EntityNotFoundException("Не найдена культура с id: " + cropId));

        var field = fRepository.findById(fieldId)
                .orElseThrow(() -> new EntityNotFoundException("Не найден поле с id: " + fieldId));
        cropRotation.setCrop(crop);
        cropRotation.setField(field);
        CRRepository.save(cropRotation);

        return CRMapper.responseCRWithField(new FandCRandCImpl(field, cropRotation, crop));
    }

    public ResponseCRWithField getCR(Long id) throws WebClientRequestException  {
        var cropRotation = CRRepository.findCropRotationById(id);
        if(cropRotation == null){
            throw new EntityNotFoundException("Не найден севооборот с id: " + id);
        }
        return CRMapper.responseCRWithField(cropRotation);
    }

    public ResponseCRWithField updateCR(Long id, CropRotation newCropRotation, Long cropId)throws WebClientRequestException {
        var cropRotation = CRRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Не найден севооборот с id: " + id));
        var newCrop = cropsRepository
                .findById(cropId).orElseThrow(() -> new EntityNotFoundException("Не найдена культура с id: " + cropId));
        CRMapper.newCRToCR(cropRotation, newCropRotation);
        cropRotation.setCrop(newCrop);
        return CRMapper.responseCRWithField(new FandCRandCImpl(cropRotation.getField(), CRRepository.save(cropRotation), newCrop));

    }

    public void deleteCR(Long id) {
        var cropRotation = CRRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Не найден севооборот с id: " + id));
        var crop = cropRotation.getCrop();
        cropRotation.setCrop(null);
        crop.getCropRotations().remove(cropRotation);
        CRRepository.delete(cropRotation);
    }
}
