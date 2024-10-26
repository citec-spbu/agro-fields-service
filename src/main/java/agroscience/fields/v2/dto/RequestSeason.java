package agroscience.fields.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class RequestSeason {

  @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
  private String seasonId;
  private LocalDate startDate;
  private LocalDate endDate;
  private String description;

}