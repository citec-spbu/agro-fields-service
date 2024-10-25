package agroscience.fields.v2.dto;

import agroscience.fields.dto.field.CoordinatesDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Data;

@Data
public class ContourDTO {

  @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
  private String contourId;
  @Pattern(regexp = "^([A-Fa-f0-9]{6})$", message = "The color must be written in HEX format")
  private String color;
  private String squareArea;
  private List<CoordinatesDTO> coordinates;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<CropRotationV2DTO> cropRotations;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<SoilCompositionDTO> soilCompositions;

}