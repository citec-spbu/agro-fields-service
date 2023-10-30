package agroscience.fields.dto.field;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RequestField {
    private Long organizationId;
    private String name;
    private String squareArea;
    private GeomDTO geom;
    private String description;
    @Pattern(regexp = "^([A-Fa-f0-9]{6})$",message = "Цвет записан не в HEX")
    private String color;
    private String activityStart;
    private String activityEnd;
}
