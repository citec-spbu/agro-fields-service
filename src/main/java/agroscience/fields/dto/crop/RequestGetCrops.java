package agroscience.fields.dto.crop;

import agroscience.fields.dto.Page;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RequestGetCrops extends Page {
    @Size(max = 50)
    private String name;
}
