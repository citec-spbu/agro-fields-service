package agroscience.fields.controllers;

import agroscience.fields.dto.crop.RequestCrop;
import agroscience.fields.dto.crop.ResponseCrop;
import agroscience.fields.services.CropsService;
import agroscience.fields.mappers.CropMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/crops")
public class CropsController {
    private final CropsService cropsService;
    private final CropMapper cropMapper;

    @PostMapping
    @Operation(description = "Создание культуры")
    public ResponseCrop createCrop(@Valid @RequestBody RequestCrop request){
        return cropMapper.cropToResponseCrop(cropsService.createCrop(cropMapper.requestCropToCrop(request)));
    }

    @PutMapping
    @Operation(description = "Обновление культуры")
    public ResponseCrop updateCrop(@Valid@Min(1) Long cropId, @Valid @RequestBody RequestCrop request){
        return cropMapper.cropToResponseCrop(cropsService.updateCrop(cropId, cropMapper.requestCropToCrop(request)));
    }

    @DeleteMapping
    @Operation(description = "Удаление культуры, вместе с ней удалятся и все севообороты, которые на неё ссылаются")
    public ResponseEntity<Void> deleteCrop(@Valid@Min(1) Long cropId){
        cropsService.deleteCropById(cropId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(description = "Получение культуры по id")
    public ResponseCrop getCrop(@Valid@Min(1) Long cropId){
        return cropMapper.cropToResponseCrop(cropsService.getCrop(cropId));
    }

}
