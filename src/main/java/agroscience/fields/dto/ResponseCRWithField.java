package agroscience.fields.dto;

import agroscience.fields.dto.crop.ResponseCrop;
import lombok.Data;

@Data
public class ResponseCRWithField {
    private Long id;
    private ResponseCrop crop;
    private String startDate;
    private String endDate;
    private String description;
    private ResponseFieldName field;
}
