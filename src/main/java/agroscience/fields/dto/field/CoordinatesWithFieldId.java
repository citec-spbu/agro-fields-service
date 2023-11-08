package agroscience.fields.dto.field;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoordinatesWithFieldId extends CoordinatesDTO{
    private final Long id;

    public CoordinatesWithFieldId(Long id, Double longitude, Double latitude) {
        super(longitude, latitude);
        this.id = id;
    }
}
