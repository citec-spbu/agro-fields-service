package agroscience.fields.dto.crop;

import agroscience.fields.dto.Page;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RequestGetCrops extends Page {
    @Size(max = 50)
    @JsonProperty("name")
    private String name;
}
