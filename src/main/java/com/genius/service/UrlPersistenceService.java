package com.genius.service;


import com.genius.pojo.ShortUrlMapEntity;
import com.genius.repository.ShortUrlMapRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * URL persistence operation service
 *
 * @author dongli
 * @date 2022-03-01
 */
@Slf4j
@Service
public class UrlPersistenceService {

    private ShortUrlMapRepository shortUrlMapRepository;

    @Autowired
    public UrlPersistenceService(ShortUrlMapRepository shortUrlMapRepository){
        this.shortUrlMapRepository = shortUrlMapRepository;
    }

    public Mono<ShortUrlMapEntity> findById(Long id){
        return shortUrlMapRepository.findById(id);
    }

    /**
     * find original long url by param shortUrl
     * @param shortUrl short url
     * @return Mono<String> Mono object include original long url
     */
    Mono<String> findLongUrlByShortUrl(String shortUrl){
        log.info("find original long url in database by short url, param shortUrl:{}", shortUrl);
        return shortUrlMapRepository.queryLongUrlByShortUrl(shortUrl);
    }

    /**
     * find short url by param original long url
     * @param longUrl original long url
     * @return Mono<String> Mono object include original short url
     */
    Mono<String> findShortUrlByLongUrl(String longUrl){
        log.info("find short url in database by original long url, param longUrl:{}", longUrl);
        return shortUrlMapRepository.queryShortUrlByLongUrl(longUrl);
    }

    /**
     * Persistent URL map entity
     * @param shortUrlMapEntity map entity fields must include longUrl and shortUrl
     * @return Mono<ShortUrlMapEntity> Mono object include ShortUrlMapEntity
     */
    Mono<ShortUrlMapEntity> saveMapEntity(ShortUrlMapEntity shortUrlMapEntity){
        log.info("save ShortUrlMapEntity to database by entity.");
        return shortUrlMapRepository.save(shortUrlMapEntity);
    }

}
