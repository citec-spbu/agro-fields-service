package agroscience.fields.dto.croprotation;

import agroscience.fields.dto.crop.ResponseCrop;
import lombok.Data;

@Data
public class ResponseCRForF {
    private Long id;
    private ResponseCrop crop;
    private String description;
    private String startDate;
    private String endDate;
}
