package agroscience.fields.v2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "soil_compositions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoilComposition {

  @Id
  @Column(name = "id")
  private UUID soilCompositionId;

  @Column(name = "contour_id")
  private UUID contourId;

  @Column(name = "ph")
  private String soilCompositionPh;

  @Column(name = "sample_date")
  private LocalDate soilCompositionSampleDate;

  @Column(name = "organic_matter")
  private String soilCompositionOrganicMatter;

  @Column(name = "mobile_p")
  private String soilCompositionMobileP;

  @Column(name = "mobile_k")
  private String soilCompositionMobileK;

  @Column(name = "mobile_s")
  private String soilCompositionMobileS;

  @Column(name = "nitrate_n")
  private String soilCompositionNitrateN;

  @Column(name = "ammonium_n")
  private String soilCompositionAmmoniumN;

  @Column(name = "hydrolytic_acidity")
  private String soilCompositionHydrolyticAcidity;

  @Column(name = "ca_exchange")
  private String soilCompositionCaExchange;

  @Column(name = "mg_exchange")
  private String soilCompositionMgExchange;

  @Column(name = "b")
  private String soilCompositionB;

  @Column(name = "co")
  private String soilCompositionCo;

  @Column(name = "mn")
  private String soilCompositionMn;

  @Column(name = "zn")
  private String soilCompositionZn;

}
