package agroscience.fields.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CropRotationV2DTO {

  @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
  private String cropRotationId;
  @Pattern(regexp = "^[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}$", message = "Ожидается UUID")
  private String contourID;
  private LocalDate startDate;
  private LocalDate endDate;
  private String culture;
  private String cultivar;
  private String description;

}
