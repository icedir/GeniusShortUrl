package com.genius.common;

import java.io.Serializable;

/**
 * @author icedir
 * @param <T>
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 3886133510113334083L;
    private StatusCode code;
    private String message;
    private T data;

    /**
     * 无参构造方法中将响应码置为DefaultStatus中的SUCCESS
     */
    public BaseResponse() {
        this.setCode(DefaultStatusEnum.SUCCESS);
        this.message = DefaultStatusEnum.SUCCESS.message();
    }

    public BaseResponse(T data) {
        this();
        this.data = data;
    }

    public StatusCode getCode() {
        return code;
    }

    public BaseResponse<T> setCode(StatusCode code) {
        this.code = code;
        this.message = code.message();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> BaseResponse<T> with(StatusCode code) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = code;
        response.message = code.message();
        return response;
    }

    public static <T> BaseResponse<T> with(StatusCode code, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = code;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> with(StatusCode code, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = code;
        response.message = code.message();
        response.data = data;
        return response;
    }

    public static <T> BaseResponse<T> with(StatusCode code, String message, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = code;
        response.message = message;
        response.data = data;
        return response;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code.code() +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
