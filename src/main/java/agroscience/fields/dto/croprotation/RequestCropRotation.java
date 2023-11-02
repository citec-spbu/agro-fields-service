package agroscience.fields.dto.croprotation;

import  agroscience.fields.dto.TimeDTO;
import agroscience.fields.exceptions.validation.constraints.LocalDateFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@LocalDateFormat
public class RequestCropRotation implements TimeDTO {
    @NotNull
    @Min(value = 1, message = "Id не может быть меньше 1")
    private Long fieldId;
    @NotNull
    @Min(value = 1, message = "Id не может быть меньше 1")
    private Long cropId;

    private String startDate;
    private String endDate;

    @Size(max = 256, message = "Максимум 256 символов")
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
