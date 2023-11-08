package agroscience.fields.dto.field;

import agroscience.fields.dto.ResponseMeteo;
import agroscience.fields.dto.croprotation.ResponseCRForF;
import agroscience.fields.dto.soil.ResponseSoilForF;
import lombok.Data;

import java.util.List;

@Data
public class ResponseFullField{
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
    private ResponseSoilForF soil;
    private List<ResponseMeteo> meteoList;
}
