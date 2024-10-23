package agroscience.fields.v2.dto.soilcompositions;

import java.time.LocalDate;
import lombok.Data;

@Data
@SuppressWarnings("MemberName")
public class RequestSoilComposition { // TODO Add contour_id

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
