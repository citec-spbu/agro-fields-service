package agroscience.fields.v2.dto.fields;

import agroscience.fields.dto.field.CoordinatesDTO;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Data;

@Data
public class RequestContour {

  @Pattern(regexp = "^([A-Fa-f0-9]{6})$", message = "The color must be written in HEX format")
  private String color;
  private String squareArea;
  private List<CoordinatesDTO> coordinates;

}
