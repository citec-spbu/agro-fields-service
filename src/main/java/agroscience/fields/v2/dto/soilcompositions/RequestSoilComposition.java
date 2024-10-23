package agroscience.fields.v2.dto.soilcompositions;

import java.time.LocalDate;
import lombok.Data;


@Data
public class RequestSoilComposition {

  private String soilCompositionPh;
  private LocalDate soilCompositionSampleDate;
  private String soilCompositionOrganicMatter;
  private String soilCompositionMobileP;
  private String soilCompositionMobileK;
  private String soilCompositionMobileS;
  private String soilCompositionNitrateN;
  private String soilCompositionAmmoniumN;
  private String soilCompositionHydrolyticAcidity;
  private String soilCompositionCaExchange;
  private String soilCompositionMgExchange;
  private String soilCompositionB;
  private String soilCompositionCo;
  private String soilCompositionMn;
  private String soilCompositionZn;

}
