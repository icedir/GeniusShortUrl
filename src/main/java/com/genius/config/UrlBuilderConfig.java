package com.genius.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author icedir
 * @date 2022-04-04
 */
@Data
@Configuration
@PropertySource(value = "classpath:url-builder-config.yml", factory = YamlPropertySourceFactory.class, encoding = "UTF-8")
@ConfigurationProperties(prefix="url")
public class UrlBuilderConfig {

    private String prefix;
}
