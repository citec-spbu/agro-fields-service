package agroscience.fields.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class PostgreProperties {
    private String username;
    private String password;
    private String url;
    private String driverClassName;
}
