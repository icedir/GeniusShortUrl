<p align="center"><img alt="Genius Logo" src="website/static/img/genius-logo.png" width="40%"/></p>
<p align="center">A simple and efficient short URL conversion service based on SpringBoot</p>

What is GeniusShortUrl?
---

GeniusShortUrl is an open source **reactive service** that converts long links to short links based on **SpringBoot Webflux**, which can quickly respond to link conversions and jumps.

- __Blazing Performance__

GeniusShortUrl is developed based on SpringBoot Webflux and can naturally receive more concurrent requests. And the project uses Caffeine cache, when the behavior of hitting the cache occurs, the response is extremely efficient.

- __Easy To Use__

GeniusShortUrl only needs very simple configuration, such as mysql database connection and cache configuration to run, which is easy to start quickly.

- __Tiny Size__

GeniusShortUrl is only about 39MB after packaging, and there is room for further optimization, which is also very friendly to memory usage.

## Api

- **```/url/shorten```** Convert incoming long URL to short URL and cache.

- **```/url/jump```** Jump from the visited short link to the long link (original URL), and extract the data from the cache if it hits the cache.

## Design Overview

## Getting Started

1. Configure ```/src/main/resources/application-prod.yml```, modify ```r2dbc.url.r2dbc.mysql``` related properties.
2. Execute the SQL script located in the ```/resources/init-sqls/``` path in the configured mysql database to initialize the database tables.
3. Modify the file ```url-builder-config.yml``` to configure the short chain prefix and suffix (the suffix will be used after a hash collision occurs when generating a short chain).
4. Configurable log output if necessary: ```​​log4j2-prod.yml```.

## Roadmap

- [Roadmap 2022](https://github.com/icedir/GeniusShortUrl/issues/1)