package agroscience.fields.dto.field;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoordinatesWithFieldId extends CoordinatesDTO{
    private final Long fieldId;

    public CoordinatesWithFieldId(Long fieldId, Double longitude, Double latitude) {
        super(longitude, latitude);
        this.fieldId = fieldId;
    }
}
