package com.genius.common;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 序列化时只显示code，message以BaseResponse中的为准
 * @author icedir
 */
public interface StatusCode extends Serializable {
    /**
     * return code
     * @return code
     */
    @JsonValue
    int code();

    /**
     * not required
     * @return message
     */
    String message();
}
