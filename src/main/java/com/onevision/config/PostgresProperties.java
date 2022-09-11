package com.onevision.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@Setter
@PropertySource("classpath:database.properties")
@ConfigurationProperties(prefix = "postgres")
public class PostgresProperties {

    private String url;
    private String username;
    private String password;
    private String driver;

}
