package agroscience.fields.v2.dto.seasons;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class RequestSeasons {

  private UUID seasonId;
  private LocalDate startDate;
  private LocalDate endDate;
  private String description;
  private UUID organizationId;

}
