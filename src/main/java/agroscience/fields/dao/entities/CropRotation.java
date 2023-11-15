package agroscience.fields.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "crop_rotations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropRotation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crop_rotations_id_seq")
    @SequenceGenerator(name = "crop_rotations_id_seq", sequenceName = "crop_rotations_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "field_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Field field;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "crop_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Crop crop;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date",nullable = false)
    private LocalDate endDate;

    @Column(name = "description", length = 256, nullable = false)
    private String description;

}
