package agroscience.fields.v2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "crop_rotations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CropRotationV2 extends ArchivedEntity {

  @Id
  @Column(name = "crop_rotation_id")
  private UUID cropRotationId;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @Column(name = "culture", length = 20)
  private String culture;

  @Column(name = "cultivar", length = 20)
  private String cultivar;

  @Column(name = "description", length = 256)
  private String description;

  @ManyToOne
  @JoinColumn(name = "contour_id", nullable = false)
  private Contour contour;

}