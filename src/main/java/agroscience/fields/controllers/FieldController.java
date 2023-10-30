package agroscience.fields.controllers;

import agroscience.fields.dto.Page;
import agroscience.fields.dto.field.RequestField;
import agroscience.fields.dto.field.ResponseField;
import agroscience.fields.dto.field.ResponseFieldPreview;
import agroscience.fields.mappers.FieldMapper;
import agroscience.fields.services.FieldService;
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
    public ResponseFieldPreview createField(@Valid@RequestBody RequestField fieldRequest){
        return fMapper.fieldToResponseFPreview(fieldService.createField(fMapper.requestFieldToField(fieldRequest)));
    }

    @GetMapping
    public ResponseField getField(Long fieldId){
        return fieldService.getFieldWithCR(fieldId);
    }

    @GetMapping("/organization/preview")
    public List<ResponseField> getFields(@Valid @Min(1) Long organizationId, @Valid Page page){
        return fieldService.getFields(organizationId, PageRequest.of(page.getPage(), page.getSize())).stream()
                .map(fMapper::fieldToResponseField).toList();
    }
    @GetMapping("preview/{fieldId}")
    public ResponseFieldPreview getFieldPreview(@PathVariable Long fieldId){
        return fMapper.fieldToResponseFPreview(fieldService.getFieldWithCurrentCrop(fieldId));
    }

    @PutMapping
    public ResponseFieldPreview updateField(@Valid@Min(1) Long fieldId,
                                            @Valid@RequestBody RequestField request){
        return fMapper.fieldToResponseFPreview(fieldService.updateField(fieldId, request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteField(@Valid@Min(1)Long fieldId){
        fieldService.deleteField(fieldId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/organization")
    public List<ResponseField> getFieldsPreview(@Valid @Min(1) Long organizationId, @Valid Page page){
        return fieldService.getFieldsForPreview(organizationId, PageRequest.of(page.getPage(), page.getSize())).stream()
                .map(fMapper::fieldToResponseField).toList();
    }
}