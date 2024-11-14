package agroscience.fields.v2.controllers;

import generated.agroscience.fields.api.MeteoSlaveApi;
import generated.agroscience.fields.api.model.MeteoResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeteoSlaveController implements MeteoSlaveApi {

  @Override
  public List<MeteoResponse> coordinatesForMeteoService() {
    return List.of();
    // TODO Отдавать координаты всех полей, нужно использовать JdbcTemplate. В сервисе можно использовать MeteoResponse
  }

}