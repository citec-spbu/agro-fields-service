package agroscience.fields.v2.controllers;

import agroscience.fields.dto.cultivar.ResponseCultivar;
import agroscience.fields.mappers.CultivarMapper;
import agroscience.fields.services.CultivarsService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/fields-service/crops")
public class CultivarsController {

  private final CultivarsService cultivarsService;
  private final CultivarMapper cultivarMapper;

  @GetMapping("/{cropId}/cultivars")
  public List<ResponseCultivar> getCultivars(
          @PathVariable Long cropId,
          @RequestParam(name = "name", defaultValue = "") @Size(max = 80) String name,
          @RequestParam(name = "page", defaultValue = "0") @Min(0) int page,
          @RequestParam(name = "size", defaultValue = "5000") @Min(1) int size
  ) {
    return cultivarsService.getCultivars(cropId, name, page, size).stream()
            .map(cultivarMapper::map)
            .toList();
  }
}
