package agroscience.fields.dto;

import agroscience.fields.utilities.validation.constraints.LocalDateTimeFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@LocalDateTimeFormat
public class RequestCropRotation {
    @NotNull
    @Min(value = 1, message = "Id не может быть меньше 1")
    private Long fieldId;
    @NotNull
    @Min(value = 1, message = "Id не может быть меньше 1")
    private Long cropId;
    @NotBlank(message = "Нужно заполнить")
    private String startDate;
    @NotBlank(message = "Нужно заполнить")
    private String endDate;
    @Size(max = 256, message = "Максимум 256 символов")
    private String description;
}
