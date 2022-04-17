package com.genius.util;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * Hash algorithm processing tool class
 *
 * @author icedir
 * @date 2022-03-01
 */
@Slf4j
public class HashUtil {

    /**
     * Function use murmur3 hash(guava)
     * In order to avoid negative numbers in 32-bit hashes, perform an unsigned right shift operation
     *
     * @param param string need hash
     * @return positive integer
     */
    public static int murmurHash32bit(String param){
        int hashNum = Hashing.murmur3_32_fixed().hashString(param, StandardCharsets.UTF_8).asInt();
        return hashNum >>> 1;
    }
}
