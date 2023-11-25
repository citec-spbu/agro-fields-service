package agroscience.fields.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final PostgreProperties postgreProperties;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgreProperties.getDriverClassName());
        dataSource.setUrl(postgreProperties.getUrl());
        dataSource.setUsername(postgreProperties.getUsername());
        dataSource.setPassword(postgreProperties.getPassword());
        return dataSource;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
