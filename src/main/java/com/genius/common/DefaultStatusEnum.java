package com.genius.common;

/**
 * @author icedir
 */
public enum DefaultStatusEnum implements StatusCode {
    /**
     * 常规返回值
     */
    SUCCESS(200, "Operation Success"),
    PARAM_ERROR(400, "Invalid parameters"),
    NOT_FOUND(404, "Resource not found"),
    FAILURE(500, "Application internal error");

    /**
     * 返回code
     */
    private final int code;
    /**
     * message
     */
    private final String message;

    DefaultStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }
}
