package agroscience.fields.controllers;

import agroscience.fields.dto.soil.RequestSoil;
import agroscience.fields.dto.soil.ResponseSoil;
import agroscience.fields.mappers.SoilMapper;
import agroscience.fields.services.SoilService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/soil")
public class SoilController {
    private final SoilService soilService;
    private final SoilMapper soilMapper;

    @PostMapping
    @Operation(description = "Создание агрохимии")
    public ResponseSoil createSoil(@Valid @RequestBody RequestSoil request){
        return soilMapper.soilToResponseSoil(soilService.createSoil(soilMapper.requestSoilToSoil(request), request.getFieldId()));
    }

//    @PutMapping
//    @Operation(description = "Обновление культуры")
//    public ResponseCrop updateCrop(@Valid@Min(1) Long cropId, @Valid @RequestBody RequestCrop request){
//        return cropMapper.cropToResponseCrop(cropsService.updateCrop(cropId, cropMapper.requestCropToCrop(request)));
//    }
//
    @DeleteMapping
    @Operation(description = "Удаление культуры, вместе с ней удалятся и все севообороты, которые на неё ссылаются")
    public ResponseEntity<Void> deleteCrop(@Valid@Min(1) Long soilId){
        soilService.deleteSoilById(soilId);
        return ResponseEntity.noContent().build();
    }
}
