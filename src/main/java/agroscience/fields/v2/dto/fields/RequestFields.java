package agroscience.fields.v2.dto.fields;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestFields {
  private UUID fieldId;
  private String name;
  private String description;
  private UUID seasonId;
}
