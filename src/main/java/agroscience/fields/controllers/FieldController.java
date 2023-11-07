package agroscience.fields.controllers;

import agroscience.fields.dto.Page;
import agroscience.fields.dto.field.*;
import agroscience.fields.mappers.FieldMapper;
import agroscience.fields.services.FieldService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fields")
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;
    private final FieldMapper fMapper;

    @PostMapping
    @Operation(description = "Создание поля")
    public ResponseFieldPreview createField(@Valid@RequestBody RequestField fieldRequest){
        return fMapper.fieldToResponseFPreview(fieldService.createField(fMapper.requestFieldToField(fieldRequest)));
    }

    @GetMapping
    @Operation(description = "Получение поля с полной информацией")
    public ResponseFullField getField(Long fieldId){
        return fieldService.getFullField(fieldId);
    }

    @GetMapping("/organization")
    @Operation(description = "Получение полей организации с текущими севооборотами")
    public List<ResponseFieldWithCR> getFields(@Valid @Min(1) Long organizationId, @Valid Page page){
        return fieldService.getFields(organizationId, PageRequest.of(page.getPage(), page.getSize())).stream()
                .map(fMapper::fieldToResponseField).toList();
    }
    @GetMapping("preview")
    @Operation(description = "Получение поля вместе с растущей на ней культурой(не севооборот, а именно культура)")
    public ResponseFieldPreview getFieldPreview(@Valid@Min(1) Long fieldId){
        return fMapper.fieldToResponseFPreview(fieldService.getFieldWithCurrentCrop(fieldId));
    }

    @PutMapping
    @Operation(description = "Обновление поля")
    public ResponseFieldPreview updateField(@Valid@Min(1) Long fieldId,
                                            @Valid@RequestBody RequestField request){
        return fMapper.fieldToResponseFPreview(fieldService.updateField(fieldId, request));
    }

    @DeleteMapping
    @Operation(description = "Удаление поля с концами, севообороты этого поля тоже удалятся из базы данных")
    public ResponseEntity<Void> deleteField(@Valid@Min(1)Long fieldId){
        fieldService.deleteField(fieldId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/organization/preview")
    @Operation(description = "Получение полей организации вместе с растущими на них культурами(не севообороты, а именно культуры)")
    public List<ResponseFieldPreview> getFieldsPreview(@Valid @Min(1) Long organizationId, @Valid Page page){
        return fieldService.getFieldsForPreview(organizationId, PageRequest.of(page.getPage(), page.getSize())).stream()
                .map(fMapper::fieldToResponseFPreview).toList();
    }

    @GetMapping("/meteo/all-coordinates")
    @Operation(description = "Получение середин всех контуров")
    public List<CoordinatesWithFieldId> getAllCoordinates(){
        return fieldService.getAllCoordinates();
    }
}