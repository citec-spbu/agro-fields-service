package agroscience.fields.v2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "seasons")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Fields {
  @Id
  @Column(name = "field_id")
  private UUID fieldId;

  @Column(name = "field_name")
  private String fieldName;

  @Column(name = "field_description")
  private String fieldDescription;

  @Column(name = "season_id")
  private UUID seasonId;

  @OneToMany(mappedBy = "field", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
          fetch = FetchType.EAGER, orphanRemoval = true) // FetchType - сразу запрос на поля или нет
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<Contours>  contours;
}
