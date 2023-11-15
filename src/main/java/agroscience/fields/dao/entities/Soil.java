package agroscience.fields.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "soil")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Soil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soil_id_seq")
    @SequenceGenerator(name = "soil_id_seq", sequenceName = "soil_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Field field;

    @Column(name = "ph")
    private String ph;

    @Column(name = "sample_date", nullable = false, unique = true)
    private LocalDate sampleDate;

    @Column(name = "organic_matter")
    private String organicMatter;

    @Column(name = "mobile_p")
    private String mobileP;

    @Column(name = "mobile_k")
    private String mobileK;

    @Column(name = "mobile_s")
    private String mobileS;

    @Column(name = "nitrate_n")
    private String nitrateN;

    @Column(name = "ammonium_n")
    private String ammoniumN;

    @Column(name = "hydrolytic_acidity")
    private String hydrolyticAcidity;

    @Column(name = "ca_exchange")
    private String caExchange;

    @Column(name = "mg_exchange")
    private String mgExchange;

    @Column(name = "b")
    private String b;

    @Column(name = "co")
    private String co;

    @Column(name = "mn")
    private String mn;

    @Column(name = "zn")
    private String zn;
}
