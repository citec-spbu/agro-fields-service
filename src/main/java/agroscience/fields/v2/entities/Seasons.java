package agroscience.fields.v2.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "seasons")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Seasons {
    @Id
    @Column(name = "season_id")
    private UUID seasonId;

    @Column(name = "season_start_date")
    private LocalDate startDate;

    @Column(name = "season_end_date")
    private LocalDate endDate;

    @Column(name = "season_description")
    private String description;

    @Column(name = "organization_id")
    private UUID organizationId;
}