package com.genius.util;

import lombok.extern.slf4j.Slf4j;

/**
 * UrlConvertUtil
 *
 * @author icedir
 * @date 2022-03-02
 */
@Slf4j
public class UrlConvertUtil {

    /**
     * Use murmur32 hash and binary convert process url
     *
     * @param longUrl original url
     * @return shortUrl(sixty binary)
     */
    public static String convertUrlToShort(String longUrl){
        int hash = HashUtil.murmurHash32bit(longUrl);
        log.info("Original url:{}, guava murmur32 hashed value:{}", longUrl, hash);
        String scale62 = ScaleConversionUtil.convert10To62(hash);
        log.info("Original url:{}, the sixty binary converted value:{}", longUrl, scale62);
        return scale62;
    }
}
