package agroscience.fields.services;

import agroscience.fields.dao.models.FandCRandC;
import agroscience.fields.dao.models.FandCRandCImpl;
import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dao.repositories.CropRotationRepository;
import agroscience.fields.dao.repositories.CropsRepository;
import agroscience.fields.dao.repositories.FieldRepository;
import agroscience.fields.dao.repositories.JbdcDao;
import agroscience.fields.dto.croprotation.ResponseCRWithField;
import agroscience.fields.exceptions.AuthException;
import agroscience.fields.mappers.CropRotationMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CropRotationsService {
    private final CropRotationRepository CRRepository;
    private final CropsRepository cropsRepository;
    private final CropRotationMapper CRMapper;
    private final FieldRepository fRepository;
    private final JbdcDao dao;

    public List<CropRotation> getAllByFieldId(Long orgId, Long fieldId, PageRequest request) {
        var fieldOrgId = dao.getOrgIdByFieldId(fieldId);
        if(!Objects.equals(fieldOrgId, orgId)){
            throw new AuthException("Вы не принадлежите организации с id " + fieldOrgId);

        }
        return CRRepository.findAllByFieldId(fieldId, request).toList();
    }

    public List<FandCRandC> getAll(Long orgId, PageRequest request) {
        return CRRepository.findAllByOrgId(orgId, request).toList();
    }

    public ResponseCRWithField createCR(Long orgId, CropRotation cropRotation, Long cropId, Long fieldId){
        var crop = cropsRepository.findById(cropId)
                .orElseThrow(() -> new EntityNotFoundException("Не найдена культура с id: " + cropId));

        var field = fRepository.findById(fieldId)
                .orElseThrow(() -> new EntityNotFoundException("Не найден поле с id: " + fieldId));

        if (!Objects.equals(field.getOrganizationId(), orgId)){
            throw new AuthException("Вы не принадлежите организации с id " + field.getOrganizationId());
        }

        cropRotation.setCrop(crop);
        cropRotation.setField(field);
        CRRepository.save(cropRotation);

        return CRMapper.responseCRWithField(new FandCRandCImpl(field, cropRotation, crop));
    }

    public ResponseCRWithField getCR(Long id, Long orgId) throws WebClientRequestException  {
        var cropRotation = CRRepository.findCropRotationById(id);

        var cropRotationOrgId = cropRotation.getField().getOrganizationId();

        if (!Objects.equals(cropRotationOrgId, orgId)){
            throw new AuthException("Вы не принадлежите организации с id " + cropRotationOrgId);
        }

        if(cropRotation == null){
            throw new EntityNotFoundException("Не найден севооборот с id: " + id);
        }
        return CRMapper.responseCRWithField(cropRotation);
    }

    public ResponseCRWithField updateCR(Long orgId, Long id, CropRotation newCropRotation, Long cropId)throws WebClientRequestException {
        var cropRotation = CRRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Не найден севооборот с id: " + id));

        var cropRotationOrgId = cropRotation.getField().getOrganizationId();

        if (!Objects.equals(cropRotationOrgId, orgId)){
            throw new AuthException("Вы не принадлежите организации с id " + cropRotationOrgId);
        }

        var newCrop = cropsRepository
                .findById(cropId).orElseThrow(() -> new EntityNotFoundException("Не найдена культура с id: " + cropId));
        CRMapper.newCRToCR(cropRotation, newCropRotation);
        cropRotation.setCrop(newCrop);
        return CRMapper.responseCRWithField(new FandCRandCImpl(cropRotation.getField(), CRRepository.save(cropRotation), newCrop));

    }

    public void deleteCR(Long id, Long orgId) {
        var cropRotation = CRRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Не найден севооборот с id: " + id));

        var cropRotationOrgId = cropRotation.getField().getOrganizationId();

        if (!Objects.equals(cropRotationOrgId, orgId)){
            throw new AuthException("Вы не принадлежите организации с id " + cropRotationOrgId);
        }

        var crop = cropRotation.getCrop();
        cropRotation.setCrop(null);
        crop.getCropRotations().remove(cropRotation);
        CRRepository.delete(cropRotation);
    }
}
