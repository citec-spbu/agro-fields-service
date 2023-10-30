package agroscience.fields.dto;

import agroscience.fields.dto.croprotation.ResponseCRForF;
import lombok.Data;

import java.util.List;
@Data
public class ResponseListCropRotationsForField {
    private Integer fieldId;
    List<ResponseCRForF> cropRotations;
}
