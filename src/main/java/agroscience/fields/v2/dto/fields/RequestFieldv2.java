package agroscience.fields.v2.dto.fields;

import lombok.Data;

import java.util.List;

@Data
public class RequestFieldv2 {
  private String name;
  private String seasonId;
  private List<RequestContour> contours;
}
