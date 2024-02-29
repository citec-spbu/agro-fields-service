package agroscience.fields.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "crop_rotation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropRotation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crop_rotation_crop_rotation_id_seq")
    @SequenceGenerator(name = "crop_rotation_crop_rotation_id_seq", sequenceName = "crop_rotation_crop_rotation_id_seq", allocationSize = 1)
    @Column(name = "crop_rotation_id")
    private Long cropRotationId;

    @JoinColumn(name = "field_id", referencedColumnName = "field_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Field field;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "crop_id", referencedColumnName = "crop_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Crop crop;

    @Column(name = "crop_rotation_start_date",nullable = false)
    private LocalDate cropRotationStartDate;

    @Column(name = "crop_rotation_end_date",nullable = false)
    private LocalDate cropRotationEndDate;

    @Column(name = "crop_rotation_description", length = 256, nullable = false)
    private String cropRotationDescription;

}
