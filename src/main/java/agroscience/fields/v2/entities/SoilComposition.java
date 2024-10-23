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
@SuppressWarnings("MemberName")
public class SoilComposition {

  @Id
  @Column(name = "soil_composition_id")
  private UUID soilCompositionId;

  @Column(name = "contour_id")
  private UUID contourId;

  @Column(name = "soil_composition_ph")
  private String ph;

  @Column(name = "soil_composition_sample_date")
  private LocalDate sampleDate;

  @Column(name = "soil_composition_organic_matter")
  private String organicMatter;

  @Column(name = "soil_composition_mobile_p")
  private String mobileP;

  @Column(name = "soil_composition_mobile_k")
  private String mobileK;

  @Column(name = "soil_composition_mobile_s")
  private String mobileS;

  @Column(name = "soil_composition_nitrate_n")
  private String nitrateN;

  @Column(name = "soil_composition_ammonium_n")
  private String ammoniumN;

  @Column(name = "soil_composition_hydrolytic_acidity")
  private String hydrolyticAcidity;

  @Column(name = "soil_composition_ca_exchange")
  private String caExchange;

  @Column(name = "soil_composition_mg_exchange")
  private String mgExchange;

  @Column(name = "soil_composition_b")
  private String b;

  @Column(name = "soil_composition_co")
  private String co;

  @Column(name = "soil_composition_mn")
  private String mn;

  @Column(name = "soil_composition_zn")
  private String zn;
}
