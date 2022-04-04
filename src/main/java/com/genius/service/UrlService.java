package com.genius.service;

import com.genius.config.UrlBuilderConfig;
import com.genius.pojo.ShortUrlMapEntity;
import com.genius.util.UrlConvertUtil;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Responsible for handling URL conversion and persistence related logic
 *
 * @author icedir
 * @date 2022-03-01
 */
@Slf4j
@Service
@CacheConfig(cacheNames = {"urlLocalCache"})
public class UrlService {

    private UrlBuilderConfig urlBuilderConfig;

    private UrlPersistenceService urlPersistenceService;

    @Autowired
    public UrlService(
            UrlBuilderConfig urlBuilderConfig,
            UrlPersistenceService urlPersistenceService
    ){
        this.urlBuilderConfig = urlBuilderConfig;
        this.urlPersistenceService = urlPersistenceService;
    }

    /**
     * This function will find url in local cache first.
     * @param shortUrl short url
     * @return Mono object include original long url
     */
    @Cacheable
    public Mono<String> getOriginalUrlByShortUrl(String shortUrl){
        // TODO Find url in redis cache
        // Find url in mysql
        Mono<String> mysqlTarget = urlPersistenceService.findLongUrlByShortUrl(shortUrl);
        return
                mysqlTarget
                        // Transform Mono<Token> to Mono<Optional<Token>>.
                        // If Mono<Token> is empty, flatMap will not be triggered,
                        // then we will get a empty Mono<Optional<Token>>
                        .flatMap(mysqlMonoResult -> Mono.just(Optional.of(mysqlMonoResult)))
                        // If Mono<Optional<Token>> is empty, provide an empty Optional<Token>,
                        // then we will get a non-empty Mono<Optional<Token>> anyway
                        .defaultIfEmpty(Optional.empty())
                        // Since Mono<Optional<Token>> is not empty, flatMap will always be triggered.
                        .flatMap(mysqlMonoResultOptional -> {
                            if(!mysqlMonoResultOptional.isPresent()){
                                // If mysql result is empty, generate new short url and store it.
                                return Mono.empty();
                            }
                            String mysqlResult = mysqlMonoResultOptional.get();
                            return Mono.just(mysqlResult);
                        });
    }

    /**
     * Use long url to get corresponding short url.
     * This function will find url in local cache first.
     *
     * @param longUrl original long url.
     * @return Mono object include short url
     */
    @Cacheable
    public Mono<String> getShortUrlByLongUrl(String longUrl){
        // TODO Find url in redis cache
        // Find url in mysql
        Mono<String> mysqlTarget = urlPersistenceService.findShortUrlByLongUrl(longUrl);
        return
                mysqlTarget
                        // Transform Mono<Token> to Mono<Optional<Token>>.
                        // If Mono<Token> is empty, flatMap will not be triggered,
                        // then we will get a empty Mono<Optional<Token>>
                        .flatMap(mysqlMonoResult -> Mono.just(Optional.of(mysqlMonoResult)))
                        // If Mono<Optional<Token>> is empty, provide an empty Optional<Token>,
                        // then we will get a non-empty Mono<Optional<Token>> anyway
                        .defaultIfEmpty(Optional.empty())
                        // Since Mono<Optional<Token>> is not empty, flatMap will always be triggered.
                        .flatMap(mysqlMonoResultOptional -> {
                            if(!mysqlMonoResultOptional.isPresent()){
                                // If mysql result is empty, generate new short url and store it.
                                return generateAndStoreShortUrl(longUrl, longUrl);
                            }
                            String mysqlResult = mysqlMonoResultOptional.get();
                            log.info("Read short url from database success, current application prefix is: {}, short url value is:{}.", urlBuilderConfig.getPrefix(), mysqlResult);
                            return Mono.just(
                                    urlBuilderConfig.getPrefix() + mysqlResult
                            );
                        });
    }

    /**
     * Persistent URL map entity.
     *
     * @param shortUrlMapEntity Short url and long url map entity.
     * @return Mono object include short url with application prefix.
     */
    private Mono<String> persistenceShortUrlMap(ShortUrlMapEntity shortUrlMapEntity){
        Mono<ShortUrlMapEntity> saveResult = urlPersistenceService.saveMapEntity(shortUrlMapEntity);
        return saveResult.flatMap(mapEntity -> Mono.just(
            urlBuilderConfig.getPrefix() + mapEntity.getSUrl())
        );
    }

    /**
     * Generate a short URL and persist it.
     * If the generated short URL has a hash collision, it will recursively use the configured suffix to modify it and try to regenerate it.
     *
     * @param longUrl function param long url, in recursion it will be add suffix.
     * @param originalUrl function param original url, never changed.
     * @return Mono object include generated short url
     */
    private Mono<String> generateAndStoreShortUrl(String longUrl, String originalUrl){
        if(longUrl.equals(originalUrl)){
            log.info("Generate short url and save function status: normal.");
        }else{
            log.info("Generate short url and save function status: recursively.");
        }
        String shortUrl = UrlConvertUtil.convertUrlToShort(longUrl);
        log.info("Try to generate short url and save it, params [longUrl: {}, originalUrl: {}].", longUrl, originalUrl);
        return
                persistenceShortUrlMap(
                        ShortUrlMapEntity.builder().lUrl(originalUrl).sUrl(shortUrl).build()
                ).onErrorResume(error -> {
                            log.error("Error on ShortUrlMap entity save! %n error entity properties: [ longUrl:{}, shortUrl:{}] %n error message: {}", longUrl, shortUrl, error.getCause().getMessage());
                            String errorContext = getErrorContext(error);
                            if(errorContext.contains("Duplicate entry") && errorContext.contains("for key 's_url'")){
                                // try retry to add postfix and generate short url again
                                log.info("Catch shortUrl persistence duplicated error, try add postfix to url and put it use recursion, current application postfix is: {}", urlBuilderConfig.getPostfix());
                                return generateAndStoreShortUrl(longUrl + urlBuilderConfig.getPostfix(),originalUrl);
                            }else{
                                log.error("Short url map persistence error, details:",error);
                                return Mono.error(error);
                            }
                        }
                );
    }

    /**
     * get error message by throwable exception
     * @param throwable exception
     * @return error message string
     */
    private String getErrorContext(Throwable throwable){
        String errorContext = "";
        if(throwable.getCause() != null && throwable.getCause().getMessage() != null){
            errorContext = throwable.getCause().getMessage();
        }
        if (Strings.isNullOrEmpty(errorContext) && throwable.getMessage() != null) {
            errorContext = throwable.getMessage();
        }
        return errorContext;
    }
}
