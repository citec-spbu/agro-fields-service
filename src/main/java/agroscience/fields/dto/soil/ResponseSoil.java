package agroscience.fields.dto.soil;

import lombok.Data;

@Data
public class ResponseSoil {
    private Long id;

    private Long fieldId;

    private String ph;

    private String sampleDate;

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
