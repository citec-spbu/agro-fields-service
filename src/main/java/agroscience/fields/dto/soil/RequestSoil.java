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

    @Size(max = 10, message = "Максимум 10 символов")
    private String ph;

    @Size(max = 10, message = "Максимум 10 символов")
    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])-(\\d{4})$", message = "Дата введена неверно")
    @LocalDateFormat
    private String sampleDate;

    @Size(max = 10, message = "Максимум 10 символов")
    private String organicMatter;

    @Size(max = 10, message = "Максимум 10 символов")
    private String mobileP;

    @Size(max = 10, message = "Максимум 10 символов")
    private String mobileK;

    @Size(max = 10, message = "Максимум 10 символов")
    private String mobileS;

    @Size(max = 10, message = "Максимум 10 символов")
    private String nitrateN;

    @Size(max = 10, message = "Максимум 10 символов")
    private String ammoniumN;

    @Size(max = 10, message = "Максимум 10 символов")
    private String hydrolyticAcidity;

    @Size(max = 10, message = "Максимум 10 символов")
    private String caExchange;

    @Size(max = 10, message = "Максимум 10 символов")
    private String mgExchange;

    @Size(max = 10, message = "Максимум 10 символов")
    private String b;

    @Size(max = 10, message = "Максимум 10 символов")
    private String co;

    @Size(max = 10, message = "Максимум 10 символов")
    private String mn;

    @Size(max = 10, message = "Максимум 10 символов")
    private String zn;
}
