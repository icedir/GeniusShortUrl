package com.genius.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author icedir
 * @date 2022-03-01
 */
@Slf4j
@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping
    public void healthCheck(){
        log.info("Response success to HealthCheckApi!");
    }
}
