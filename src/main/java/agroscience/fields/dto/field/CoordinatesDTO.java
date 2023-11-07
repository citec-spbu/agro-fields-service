package agroscience.fields.dto.field;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesDTO {
    @NotNull(message = "Должно быть заполнено")
    private Double longitude;
    @NotNull(message = "Должно быть заполнено")
    private Double latitude;
}
