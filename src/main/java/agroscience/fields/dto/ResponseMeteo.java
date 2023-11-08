package agroscience.fields.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseMeteo {
    private LocalDate lastUpdate;
    private Long fieldId;
    private Double temperature;
    private Double humidity;
    private Double pressure;
}
