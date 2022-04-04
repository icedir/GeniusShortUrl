package com.genius.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Application user-defined properties class.
 * Property config file please check @PropertySource value.
 *
 * @author icedir
 * @date 2022-04-04
 */
@Data
@Configuration
@PropertySource(value = "classpath:url-builder-config.yml", factory = YamlPropertySourceFactory.class, encoding = "UTF-8")
@ConfigurationProperties(prefix="url")
public class UrlBuilderConfig {

    /**
     * Url prefix.
     * Example: https://t.cc/g/
     */
    private String prefix;

    /**
     * Url hash collision post fix.
     * When generate short url hash collision occurs, system will try to add postfix to original url and hash again.
     * Example: _duplicated
     */
    private String postfix;
}
