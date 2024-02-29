package agroscience.fields.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "soil")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Soil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soil_soil_id_seq")
    @SequenceGenerator(name = "soil_soil_id_seq", sequenceName = "soil_soil_id_seq", allocationSize = 1)
    @Column(name = "soil_id")
    private Long soilId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id", referencedColumnName = "field_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Field field;

    @Column(name = "soil_ph")
    private String soilPh;

    @Column(name = "soil_sample_date", nullable = false, unique = true)
    private LocalDate soilSampleDate;

    @Column(name = "soil_organic_matter")
    private String soilOrganicMatter;

    @Column(name = "soil_mobile_p")
    private String soilMobileP;

    @Column(name = "soil_mobile_k")
    private String soilMobileK;

    @Column(name = "soil_mobile_s")
    private String soilMobileS;

    @Column(name = "soil_nitrate_n")
    private String soilNitrateN;

    @Column(name = "soil_ammonium_n")
    private String soilAmmoniumN;

    @Column(name = "soil_hydrolytic_acidity")
    private String soilHydrolyticAcidity;

    @Column(name = "soil_ca_exchange")
    private String soilCaExchange;

    @Column(name = "soil_mg_exchange")
    private String soilMgExchange;

    @Column(name = "soil_b")
    private String soilB;

    @Column(name = "soil_co")
    private String soilCo;

    @Column(name = "soil_mn")
    private String soilMn;

    @Column(name = "soil_zn")
    private String soilZn;
}
