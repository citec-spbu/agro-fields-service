package agroscience.fields.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResponseMeteo {
    private LocalDate lastUpdate;
    private Long fieldId;
    private Double temperature;
    private Double humidity;
    private Double pressure;
}
