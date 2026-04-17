package agroscience.fields.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "cultivar")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Cultivar {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cultivar_cultivar_id_seq")
  @SequenceGenerator(name = "cultivar_cultivar_id_seq", sequenceName = "cultivar_cultivar_id_seq", allocationSize = 1)
  @Column(name = "cultivar_id")
  private Long cultivarId;

  @Column(name = "cultivar_name", length = 80, nullable = false)
  private String cultivarName;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "crop_id", nullable = false)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Crop crop;
}
