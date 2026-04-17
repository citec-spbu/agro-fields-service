package agroscience.fields.v2.controllers;

import agroscience.fields.v2.services.MeteoSlaveService;
import generated.agroscience.fields.api.MeteoSlaveApi;
import generated.agroscience.fields.api.model.ContourBaseDTO;
import generated.agroscience.fields.api.model.MeteoResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeteoSlaveController implements MeteoSlaveApi {

  private final MeteoSlaveService meteoSlaveService;

  @Override
  public List<MeteoResponse> coordinatesForMeteoService() {
    return meteoSlaveService.getAllFieldCoordinates();
  }

  @Override
  public List<ContourBaseDTO> internalFindContoursByField(UUID id) {
    return meteoSlaveService.internalFindContoursByField(id);
  }

}