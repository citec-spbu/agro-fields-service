package agroscience.fields.controllers;

import agroscience.fields.dto.Page;
import agroscience.fields.dto.croprotation.ResponseListCropRotationsForField;
import agroscience.fields.dto.croprotation.RequestCropRotation;
import agroscience.fields.dto.croprotation.ResponseCRWithField;
import agroscience.fields.exceptions.HandleErrorService;
import agroscience.fields.mappers.CropRotationMapper;
import agroscience.fields.security.AuthoriseService;
import agroscience.fields.security.Role;
import agroscience.fields.services.CropRotationsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/fields/crop-rotations")
public class CropRotationsController {
    private final CropRotationsService CRService;
    private final CropRotationMapper CRMapper;
    private final AuthoriseService auth;
    @GetMapping("/field")
    @Operation(description = "Получить севообороты для поля")
    public ResponseListCropRotationsForField getAllCropRotationsByFieldId(@Valid@Min(1) Long fieldId,
                                                                          @Valid Page p, HttpServletRequest header){
        return CRMapper.cropRotationsToList(fieldId, CRMapper.cropRotationToCropRotationResponse(CRService
                .getAllByFieldId(auth.doFilter(header, new Role.Builder().worker().organization().build()),
                        fieldId, PageRequest.of(p.getPage(), p.getSize()))));
    }

    @GetMapping("/organization")
    @Operation(description = "Получить севообороты для организации")
    public List<ResponseCRWithField> getAllCropRotations(@Valid Page p, HttpServletRequest header){
        return CRService.getAll(auth.doFilter(header, new Role.Builder().worker().organization().build()),
                        PageRequest.of(p.getPage(), p.getSize())).stream().map(CRMapper::responseCRWithField).toList();
    }

    @GetMapping()
    @Operation(description = "Получить севооборот по его id")
    public ResponseCRWithField getCR(@Valid@Min(1) Long id, HttpServletRequest header){
        return CRService.getCR(id, auth.doFilter(header, new Role.Builder().worker().organization().build()));
    }

    @PostMapping()
    @Operation(description = "Создать севооборот")
    public ResponseCRWithField createCR(@Valid@RequestBody RequestCropRotation request, HttpServletRequest header){
        return CRService.createCR(auth.doFilter(header, new Role.Builder().worker().organization().build()),
                CRMapper.CropRotatopnRequestToCropRotation(request), request.getCropId(), request.getFieldId());
    }

    @PutMapping(path = "/crop-rotations")
    @Operation(description = "Обновить севооборот по id. Нельзя поменять поле у севооборота. Вместо fieldId можно внести любое значение")
    public ResponseCRWithField updateCR(@Valid@Min(1) Long id, @Valid RequestCropRotation request, HttpServletRequest header){
        return CRService.updateCR(auth.doFilter(header, new Role.Builder().worker().organization().build()),
                id, CRMapper.CropRotatopnRequestToCropRotation(request), request.getCropId());
    }

    @DeleteMapping()
    @Operation(description = "Удаление севооборота по его id")
    public ResponseEntity<Void> deleteCR(@Valid@Min(1) Long id, HttpServletRequest header){
        CRService.deleteCR(id, auth.doFilter(header, new Role.Builder().worker().organization().build()));
        return ResponseEntity.noContent().build();
    }




}
