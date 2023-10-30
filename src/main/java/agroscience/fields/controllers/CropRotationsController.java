package agroscience.fields.controllers;

import agroscience.fields.dto.Page;
import agroscience.fields.dto.croprotation.ResponseListCropRotationsForField;
import agroscience.fields.dto.croprotation.RequestCropRotation;
import agroscience.fields.dto.croprotation.ResponseCRWithField;
import agroscience.fields.mappers.CropRotationMapper;
import agroscience.fields.services.CropRotationsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/crops")
public class CropRotationsController {
    private final CropRotationsService CRService;
    private final CropRotationMapper CRMapper;
    @GetMapping("/crop-rotations/field")
    @Operation(description = "Получить севообороты для поля")
    public ResponseListCropRotationsForField getAllCropRotationsByFieldId(@Valid@Min(1) Long fieldId,@Valid Page p){
        return CRMapper.cropRotationsToList(fieldId, CRMapper.cropRotationToCropRotationResponse(CRService
                .getAllByFieldId(fieldId, PageRequest.of(p.getPage(), p.getSize()))));
    }

    @GetMapping("/crop-rotations/organization")
    @Operation(description = "Получить севообороты для организации")
    public List<ResponseCRWithField> getAllCropRotations(@Valid@Min(1)Long orgId, @Valid Page p){
        return CRService.getAll(orgId, PageRequest.of(p.getPage(), p.getSize())).stream()
                .map(CRMapper::responseCRWithField).toList();
    }

    @GetMapping(path = "/crop-rotations")
    @Operation(description = "Получить севооборот по его id")
    public ResponseCRWithField getCR(@Valid@Min(1) Long id){
        return CRService.getCR(id);
    }

    @PostMapping(path = "/crop-rotations")
    @Operation(description = "Создать севооборот")
    public ResponseCRWithField createCR(RequestCropRotation request){
        return CRService.createCR(CRMapper.CropRotatopnRequestToCropRotation(request), request.getCropId(), request.getFieldId());
    }

    @PutMapping(path = "/crop-rotations")
    @Operation(description = "Обновить севооборот по id. Нельзя поменять поле у севооборота. Вместо fieldId можно внести любое значение")
    public ResponseCRWithField updateCR(@Valid@Min(1) Long id, RequestCropRotation request){
        return CRService.updateCR(id, CRMapper.CropRotatopnRequestToCropRotation(request), request.getCropId());
    }

    @DeleteMapping(path = "/crop-rotations")
    @Operation(description = "Удаление севооборота по его id")
    public ResponseEntity<Void> deleteCR(@Valid@Min(1) Long id){
        CRService.deleteCR(id);
        return ResponseEntity.noContent().build();
    }




}
