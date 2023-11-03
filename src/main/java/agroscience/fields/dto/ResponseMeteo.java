package agroscience.fields.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseMeteo {
    private LocalDateTime lastUpdate;
    private Long fieldId;
    private Double temperature;
    private Double humidity;
    private Double pressure;
}
