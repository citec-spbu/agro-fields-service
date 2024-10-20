package agroscience.fields.v2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "contours")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contour {

  @Id
  @Column(name = "contour_id")
  private UUID contourId;

  @Column(name = "contour_square_area", length = 40, nullable = false)
  private String contourSquareArea;

  @Column(name = "contour_geom", nullable = false, columnDefinition = "geometry(Geometry,0)")
  private Geometry contourGeom; // Тип Geometry для хранения геометрических данных

  @Column(name = "contour_description", length = 256)
  private String contourDescription;

  @Column(name = "contour_color", length = 6, nullable = false)
  private String contourColor;

  @ManyToOne
  @JoinColumn(name = "field_id")
  private FieldV2 field;

}
