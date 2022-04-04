package com.genius;

import com.genius.config.UrlBuilderConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * SpringBoot启动器
 * @author icedir
 * @date 2022-03-01
 */
@Slf4j
@EnableR2dbcRepositories
@EnableCaching
@EnableConfigurationProperties({UrlBuilderConfig.class})
@SpringBootApplication
public class GeniusShortUrlApplication {
    public static void main(String[] args) {
        log.info("GeniusShortUrl Server starting...");
        SpringApplication.run(GeniusShortUrlApplication.class, args);
        log.info("GeniusShortUrl Server start success, enjoy it!");
    }
}
