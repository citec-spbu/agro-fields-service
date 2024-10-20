package agroscience.fields.v2.entities;

import agroscience.fields.dao.entities.Field;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
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

  @JoinColumn(name = "season_id", referencedColumnName = "season_id", nullable = false)
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Seasons season;
}
