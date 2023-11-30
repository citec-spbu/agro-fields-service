package agroscience.fields.dto.croprotation;

import  agroscience.fields.dto.TimeDTO;
import agroscience.fields.exceptions.validation.constraints.LocalDateFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@LocalDateFormat
public class RequestCropRotation implements TimeDTO {

    @NotNull
    @Min(value = 1, message = "ID cannot be less than 1")
    private Long fieldId;

    @NotNull
    @Min(value = 1, message = "ID cannot be less than 1")
    private Long cropId;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])-(\\d{4})$", message = "Date entered incorrectly")
    private String startDate;
    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])-(\\d{4})$", message = "Date entered incorrectly")
    private String endDate;

    @Size(max = 256, message = "Maximum 256 characters")
    private String description;

    @Override
    public String start() {
        return startDate;
    }

    @Override
    public String end() {
        return endDate;
    }
}
