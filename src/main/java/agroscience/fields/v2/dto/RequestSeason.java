package agroscience.fields.v2.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RequestSeason {

  private LocalDate startDate;
  private LocalDate endDate;
  private String description;

}