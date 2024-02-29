package agroscience.fields.dto.croprotation;

import agroscience.fields.dto.croprotation.ResponseCRForF;
import lombok.Data;

import java.util.List;
@Data
public class ResponseListCropRotationsForField {
    private Long fieldId;
    List<ResponseCRForF> cropRotations;
}
