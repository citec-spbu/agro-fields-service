package agroscience.fields.v2.dto.seasons;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class RequestSeasons {
    private UUID seasonId;
    private Date startDate;
    private Date endDate;
    private String description;
    private UUID organizationId;
}
