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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crop_crop_id_seq")
    @SequenceGenerator(name = "crop_crop_id_seq", sequenceName = "crop_crop_id_seq", allocationSize = 1)
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
