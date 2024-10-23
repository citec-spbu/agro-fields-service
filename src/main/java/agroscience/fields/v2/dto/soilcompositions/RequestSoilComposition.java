package agroscience.fields.v2.dto.soilCompositions;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class RequestSoilComposition {
    private String ph;
    private LocalDate sampleDate;
    private String organicMatter;
    private String mobileP;
    private String mobileK;
    private String mobileS;
    private String nitrateN;
    private String ammoniumN;
    private String hydrolyticAcidity;
    private String caExchange;
    private String mgExchange;
    private String b;
    private String co;
    private String mn;
    private String zn;
}
