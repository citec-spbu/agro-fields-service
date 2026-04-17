package agroscience.fields.dto.cultivar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCultivar {

  @JsonProperty("id")
  private Long cultivarId;

  @JsonProperty("name")
  private String cultivarName;

  @JsonProperty("cropId")
  private Long cropId;
}
