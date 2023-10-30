package agroscience.fields.services;

import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dao.repositories.CropRotationRepository;
import agroscience.fields.dao.repositories.CropsRepository;
import agroscience.fields.dto.ResponseCRWithField;
import agroscience.fields.dto.ResponseFieldName;
import agroscience.fields.mappers.CropRotationMapper;
import agroscience.fields.utilities.DifficultMapping;
import agroscience.fields.utilities.RequestToFields;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CropRotationsService {
    private final CropRotationRepository CRRepository;
    private final CropsRepository cropsRepository;
    private final CropRotationMapper CRMapper;

    public List<CropRotation> getAllByFieldId(Long fieldId, PageRequest request) {
        return CRRepository.findAllByFieldId(fieldId, request).toList();
    }

    public List<ResponseCRWithField> getAll(PageRequest request) {
        List<CropRotation> cropRotations = CRRepository.findAllAsSlice(request).toList();

        List<ResponseFieldName> fieldNames = RequestToFields.getFields(webClient, cropRotations.stream()
                .map(CropRotation::getFieldId).toList());

        return DifficultMapping.listResponseCRWithFieldList(cropRotations, fieldNames);
    }

    public ResponseCRWithField createCR(CropRotation cropRotation, Long cropId) throws WebClientRequestException  {
        var crop = cropsRepository.findById(cropId)
                .orElseThrow(() -> new EntityNotFoundException("Не найден севооборот с id: " + cropId));

        cropRotation.setCrop(crop);
        CRRepository.save(cropRotation);

        List<ResponseFieldName> fieldNames = RequestToFields.getFields(webClient,
                Collections.singletonList(cropRotation.getFieldId()));

        if(fieldNames.get(0) == null){
            throw new NullPointerException();
        }

        return CRMapper.responseCRWithField(cropRotation, fieldNames.get(0));
    }

    public ResponseCRWithField getCR(Long id) throws WebClientRequestException  {
        var cropRotation = CRRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Не найден севооборот с id: " + id));

        List<ResponseFieldName> fieldNames = RequestToFields.getFields(webClient,
                Collections.singletonList(cropRotation.getFieldId()));

        if(fieldNames.get(0) == null){
            throw new NullPointerException();
        }

        return CRMapper.responseCRWithField(cropRotation, fieldNames.get(0));
    }

    public ResponseCRWithField updateCR(Long id, CropRotation newCropRotation, Long cropId)throws WebClientRequestException {

        var cropRotation = CRRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Не найден севооборот с id: " + id));

        List<ResponseFieldName> fieldNames = RequestToFields.getFields(webClient,
                Collections.singletonList(cropRotation.getFieldId()));

        if(fieldNames.get(0) == null){
            throw new EntityNotFoundException("Не найдено поля с  id: " + id);
        }

        var crop = cropRotation.getCrop();
        var newCrop = cropsRepository
                .findById(cropId).orElseThrow(() -> new EntityNotFoundException("Не найдена культура с id: " + cropId));


        System.out.println("id: "+newCrop.getId());

        crop.getCropRotations().remove(cropRotation);

        CRMapper.newCRToCR(cropRotation, newCropRotation);

        cropRotation.setCrop(newCrop);

        CRRepository.save(cropRotation);

        return CRMapper.responseCRWithField(cropRotation, fieldNames.get(0));

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
