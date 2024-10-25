package agroscience.fields.v2.dto.allrequests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Data;

@Data
public class ArField {

  @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
  private String fieldId;
  private String name;
  @Pattern(regexp = "^[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}$", message = "Ожидается UUID")
  private String seasonId;
  private String description;
  private List<ArContour> contours;

}
