package agroscience.fields.dto.field;

import agroscience.fields.dto.croprotation.ResponseCRForF;
import lombok.Data;

@Data
public class ResponseFieldWithCR {
    private Long id;
    private Long organizationId;
    private String name;
    private String squareArea;
    private GeomDTO geom;
    private String description;
    private String color;
    private String activityStart;
    private String activityEnd;
    private ResponseCRForF cropRotation;
}
