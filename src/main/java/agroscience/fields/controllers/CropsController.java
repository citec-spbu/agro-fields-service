package agroscience.fields.controllers;

import agroscience.fields.dto.RequestCrop;
import agroscience.fields.dto.crop.ResponseCrop;
import agroscience.fields.services.CropsService;
import agroscience.fields.mappers.CropMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/crops")
public class CropsController {
    private final CropsService cropsService;
    private final CropMapper cropMapper;

    @GetMapping("/currentcrop/{fieldId}")
    public ResponseCrop getCurrentCropName(@PathVariable Long fieldId){
        return cropMapper.cropToResponseCrop(cropsService.getCurrentCrop(fieldId));
    }

    @PostMapping
    public ResponseCrop createCrop(@Valid @RequestBody RequestCrop request){
        return cropMapper.cropToResponseCrop(cropsService.createCrop(cropMapper.requestCropToCrop(request)));
    }

    @PutMapping(path = "/{cropId}")
    public ResponseCrop updateCrop(@PathVariable Long cropId, @Valid @RequestBody RequestCrop request){
        return cropMapper.cropToResponseCrop(cropsService.updateCrop(cropId, cropMapper.requestCropToCrop(request)));
    }

    @DeleteMapping(path = "/{cropId}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long cropId){
        cropsService.deleteCropById(cropId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{cropId}")
    public ResponseCrop getCrop(@PathVariable Long cropId){
        return cropMapper.cropToResponseCrop(cropsService.getCrop(cropId));
    }

}
