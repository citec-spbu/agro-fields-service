package agroscience.fields.dao.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.locationtech.jts.geom.Geometry;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "fields")
@Data
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fields_id_seq")
    @SequenceGenerator(name = "fields_id_seq", sequenceName = "fields_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "organization_id", nullable = false)
    private Long organizationId;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "square_area", length = 40, nullable = false)
    private String squareArea;

    @Column(name = "geom", nullable = false, columnDefinition = "geometry(Geometry,0)")
    private Geometry geom; // Тип Geometry для хранения геометрических данных

    @Column(name = "description", length = 256)
    private String description;

    @Column(name = "color", length = 6, nullable = false)
    private String color;

    @Column(name = "activity_start")
    private LocalDate activityStart;

    @Column(name = "activity_end")
    private LocalDate activityEnd;

    @OneToMany(mappedBy = "field", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CropRotation> cropRotations;

    @OneToMany(mappedBy = "field", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Soil> soils;
}
