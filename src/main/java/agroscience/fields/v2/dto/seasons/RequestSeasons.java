package agroscience.fields.v2.dto.seasons;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RequestSeasons {

  private LocalDate seasonStartDate;
  private LocalDate seasonEndDate;
  private String seasonDescription;

}