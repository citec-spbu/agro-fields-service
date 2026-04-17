package agroscience.fields.v2.controllers;

import agroscience.fields.dto.crop.ResponseCrop;
import agroscience.fields.mappers.CropMapper;
import agroscience.fields.services.CropsService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/fields-service/crops")
public class CropsController {

  private final CropsService cropsService;
  private final CropMapper cropMapper;

  @GetMapping
  public List<ResponseCrop> getCrops(
          @RequestParam(name = "name", defaultValue = "") @Size(max = 50) String name,
          @RequestParam(name = "page", defaultValue = "0") @Min(0) int page,
          @RequestParam(name = "size", defaultValue = "5000") @Min(1) int size
  ) {
    return cropsService.getCrop(name, page, size).stream()
            .map(cropMapper::map)
            .toList();
  }
}
