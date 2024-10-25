package agroscience.fields.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Data;

@Data
@SuppressWarnings("MemberName")
public class SoilCompositionDTO {

  @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
  private String soilCompositionId;
  private String ph;
  @Pattern(regexp = "^[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}$", message = "Ожидается UUID")
  private String contourId;
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
