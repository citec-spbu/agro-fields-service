package agroscience.fields.dto.field;

import agroscience.fields.dto.TimeDTO;
import agroscience.fields.exceptions.validation.constraints.LocalDateFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@LocalDateFormat
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RequestField implements TimeDTO {

    @Size(max = 100, message = "Maximum 100 characters")
    @NotBlank(message = "Must be filled")
    private String name;

    @NotBlank(message = "Must be filled")
    @Size(max = 40, message = "Maximum 40 characters")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "The field must contain only numbers")
    private String squareArea;

    @Valid
    private GeomDTO geom;

    @Size(max = 100, message = "Maximum 256 characters")
    private String description;

    @NotBlank(message = "Must be filled")
    @Pattern(regexp = "^([A-Fa-f0-9]{6})$",message = "The color must be written in HEX format")
    private String color;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$", message = "Date entered incorrectly. Must be dd-mm-yyyy.")
    private String activityStart;
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$", message = "Date entered incorrectly. Must be dd-mm-yyyy.")
    private String activityEnd;

    @Override
    public String start() {
        return activityStart;
    }

    @Override
    public String end() {
        return activityEnd;
    }
}
