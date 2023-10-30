package agroscience.fields.dto.field;

import agroscience.fields.dto.TimeDTO;
import agroscience.fields.utilities.validation.constraints.LocalDateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@LocalDateTimeFormat
public class RequestField implements TimeDTO {
    @NotNull
    @Min(value = 1, message = "Id не может быть меньше 1")
    private Long organizationId;

    @Size(max = 100, message = "Максимум 100 символов")
    @NotBlank(message = "Должно быть заполнено")
    private String name;

    @NotBlank(message = "Должно быть заполнено")
    @Size(max = 40, message = "Максимум 40 символов")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Поле должно содержать только числа")
    private String squareArea;

    @Valid
    private GeomDTO geom;

    @Size(max = 100, message = "Максимум 256 символов")
    private String description;

    @NotBlank(message = "Должно быть заполнено")
    @Pattern(regexp = "^([A-Fa-f0-9]{6})$",message = "Цвет записан не в HEX")
    private String color;

    private String activityStart;
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
