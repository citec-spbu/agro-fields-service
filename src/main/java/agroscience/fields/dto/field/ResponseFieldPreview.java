package agroscience.fields.dto.field;

import agroscience.fields.dto.crop.ResponseCrop;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ResponseFieldPreview{
    private Long id;
    private Long organizationId;
    private String name;
    private String squareArea;
    private GeomDTO geom;
    private String description;
    @Pattern(regexp = "^([A-Fa-f0-9]{6})$",message = "The color must be written in HEX format")
    private String color;
    private String activityStart;
    private String activityEnd;
    private ResponseCrop crop;
}
