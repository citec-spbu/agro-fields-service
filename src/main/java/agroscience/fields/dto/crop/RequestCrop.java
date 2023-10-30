package agroscience.fields.dto.crop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestCrop {
    @NotBlank
    @Size(max = 50)
    private String name;
}
