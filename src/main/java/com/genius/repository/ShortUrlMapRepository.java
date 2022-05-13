package com.genius.repository;

import com.genius.pojo.ShortUrlMapEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Process ShortUrlMapEntity's relate Mysql business
 *
 * @author icedir
 * @date 2022-03-01
 */
@Repository
public interface ShortUrlMapRepository extends ReactiveCrudRepository<ShortUrlMapEntity,Long> {

    /**
     * Select map entity by map id
     * @param id 主键
     * @return Mono<ShortUrlMapEntity>
     */
    @NotNull
    @Override
    Mono<ShortUrlMapEntity> findById(@NotNull Long id);

    /**
     * Select map entity by map id
     * @param shortUrl short url
     * @return Mono<String>
     */
    @Query("select m.l_url from short_url_map m where m.s_url = :shortUrl")
    Mono<String> queryLongUrlByShortUrl(@Param("shortUrl") String shortUrl);

    /**
     * Select short url by long url
     * @param longUrl long url
     * @return Mono<String>
     */
    @Query("select m.s_url from short_url_map m where m.l_url = :longUrl")
    Mono<String> queryShortUrlByLongUrl(@Param("longUrl") String longUrl);
}
