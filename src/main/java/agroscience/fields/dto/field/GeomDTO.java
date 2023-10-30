package agroscience.fields.dto.field;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GeomDTO {
    private String type;
    private List<CoordinatesDTO> coordinates;
}
