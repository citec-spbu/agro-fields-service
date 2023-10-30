package agroscience.fields.dto.field;

import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dto.croprotation.ResponseCRForF;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ResponseField{
    private Long id;
    private Long organizationId;
    private String name;
    private String squareArea;
    private GeomDTO geom;
    private String description;
    @Pattern(regexp = "^([A-Fa-f0-9]{6})$",message = "Цвет записан не в HEX")
    private String color;
    private String activityStart;
    private String activityEnd;
    private ResponseCRForF cropRotation;
}
