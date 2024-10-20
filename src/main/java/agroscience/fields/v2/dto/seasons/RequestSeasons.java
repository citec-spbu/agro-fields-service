package agroscience.fields.v2.dto.seasons;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class RequestSeasons {

  private UUID seasonId;
  private Date startDate;
  private Date endDate;
  private String description;
  private UUID organizationId;

}
