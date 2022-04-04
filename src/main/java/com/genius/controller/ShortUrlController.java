package com.genius.controller;

import com.genius.common.BaseController;
import com.genius.common.BaseResponse;
import com.genius.dto.UrlShortenParamDto;
import com.genius.service.UrlService;
import com.genius.vo.UrlConvertResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * Short url convert and link jump controller.
 * Work with spring webflux.
 *
 * @author icedir
 * @date 2022-03-01
 */
@Slf4j
@RestController
@RequestMapping("/url")
public class ShortUrlController extends BaseController {

    private UrlService urlService;

    public ShortUrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @GetMapping(path = "/jump")
    @ResponseBody
    public Mono<ServerHttpResponse> jumpToOriginalUrl(
            ServerHttpRequest serverRequest,ServerHttpResponse serverHttpResponse
    ){
        String shortUrl = serverRequest.getQueryParams().getFirst("url");
        if(shortUrl == null || shortUrl.isEmpty()){
            log.info("Missing request parameter: url.");
            serverHttpResponse.getHeaders().setLocation(URI.create("http://www.baidu.com"));
            return Mono.just(serverHttpResponse);
        }else{
            log.info("Received a jump request, short url link is:{}", shortUrl);
            Mono<String> originalUrl = urlService.getOriginalUrlByShortUrl(shortUrl);
            return originalUrl
                    // Transform Mono<Token> to Mono<Optional<Token>>.
                    // If Mono<Token> is empty, flatMap will not be triggered,
                    // then we will get a empty Mono<Optional<Token>>
                    .flatMap(originalUrlMono -> Mono.just(Optional.of(originalUrlMono)))
                    // If Mono<Optional<Token>> is empty, provide an empty Optional<Token>,
                    // then we will get a non-empty Mono<Optional<Token>> anyway
                    .defaultIfEmpty(Optional.empty())
                    // Since Mono<Optional<Token>> is not empty, flatMap will always be triggered.
                    .flatMap(originalUrlMonoOptional -> {
                        serverHttpResponse.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
                        if(!originalUrlMonoOptional.isPresent()){
                            log.info("Received a jump request, but original url cant find! param short url: {}", shortUrl);
                            serverHttpResponse.getHeaders().setLocation(URI.create("http://www.baidu.com"));
                            return Mono.just(serverHttpResponse);
                        }
                        String mysqlResult = originalUrlMonoOptional.get();
                        serverHttpResponse.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
                        serverHttpResponse.getHeaders().setLocation(URI.create(mysqlResult));
                        log.info("Received a jump request, jump to original url, target url: {}", mysqlResult);
                        return Mono.just(serverHttpResponse);
                    });
        }
    }

    @PostMapping(path = "/shorten", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<BaseResponse> getShortUrlByLongUrl(
            @Valid @RequestBody UrlShortenParamDto urlShortenParamDto
            )
    {
            log.info("Received a shorten request, original url link:{}", urlShortenParamDto.getUrl());
            return
                    urlService.getShortUrlByLongUrl(urlShortenParamDto.getUrl())
                            .flatMap(shortUrl -> Mono.just(success(UrlConvertResultVO.builder().url(shortUrl).build())));
    }

}
