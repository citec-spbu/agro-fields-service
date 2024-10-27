package agroscience.fields.v2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class ArchivedEntity {

  @Column(name = "archived", nullable = false)
  protected boolean archived;

}