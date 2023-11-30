package agroscience.fields.dto.soil;

import agroscience.fields.exceptions.validation.constraints.LocalDateFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class RequestSoil {
    @NotNull
    private Long fieldId;

    @Size(max = 10, message = "Maximum 10 characters")
    private String ph;

    @Size(max = 10, message = "Maximum 10 characters")
    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])-(\\d{4})$", message = "Maximum 256 characters")
    @LocalDateFormat
    private String sampleDate;

    @Size(max = 10, message = "Maximum 10 characters")
    private String organicMatter;

    @Size(max = 10, message = "Maximum 10 characters")
    private String mobileP;

    @Size(max = 10, message = "Maximum 10 characters")
    private String mobileK;

    @Size(max = 10, message = "Maximum 10 characters")
    private String mobileS;

    @Size(max = 10, message = "Maximum 10 characters")
    private String nitrateN;

    @Size(max = 10, message = "Maximum 10 characters")
    private String ammoniumN;

    @Size(max = 10, message = "Maximum 10 characters")
    private String hydrolyticAcidity;

    @Size(max = 10, message = "Maximum 10 characters")
    private String caExchange;

    @Size(max = 10, message = "Maximum 10 characters")
    private String mgExchange;

    @Size(max = 10, message = "Maximum 10 characters")
    private String b;

    @Size(max = 10, message = "Maximum 10 characters")
    private String co;

    @Size(max = 10, message = "Maximum 10 characters")
    private String mn;

    @Size(max = 10, message = "Maximum 10 characters")
    private String zn;
}
