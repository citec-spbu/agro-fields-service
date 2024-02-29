package agroscience.fields.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "crop")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crop_rotation_pkey")
    @SequenceGenerator(name = "crop_rotation_pkey", sequenceName = "crop_rotation_pkey", allocationSize = 1)
    @Column(name = "crop_id")
    private Long cropId;

    @Column(name = "crop_name", length = 50, nullable = false, unique = true)
    private String cropName;

    @OneToMany(mappedBy = "crop", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CropRotation> cropRotations = new ArrayList<>();
}
