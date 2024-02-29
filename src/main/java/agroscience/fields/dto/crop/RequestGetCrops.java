package agroscience.fields.dto.crop;

import agroscience.fields.dto.Page;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RequestGetCrops extends Page {
    @Size(max = 50)
    @JsonAlias("name")
    private String cropName;
}
