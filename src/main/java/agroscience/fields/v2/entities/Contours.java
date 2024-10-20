package agroscience.fields.v2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "contours")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Contours {
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
    private Fields field;
}
