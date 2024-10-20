package agroscience.fields.v2.dto.fields;

import agroscience.fields.dto.field.CoordinatesDTO;
import agroscience.fields.dto.field.GeomDTO;
import lombok.Data;

import java.util.List;
@Data
public class RequestContour {
    private String fieldColor;
    private String fieldSquareArea;
    private List<CoordinatesDTO> coordinates;
}
