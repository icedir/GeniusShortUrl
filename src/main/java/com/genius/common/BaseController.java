package com.genius.common;

/**
 * @author icedir
 */
public class BaseController {
    public BaseResponse fail(String message){
        return BaseResponse.with(DefaultStatusEnum.FAILURE,message);
    }

    public BaseResponse failWithParam(String message){
        return BaseResponse.with(DefaultStatusEnum.PARAM_ERROR,message);
    }

    public BaseResponse success(){
        return BaseResponse.with(DefaultStatusEnum.SUCCESS);
    }

    public <T> BaseResponse<T> success(T data){
        return BaseResponse.with(DefaultStatusEnum.SUCCESS, data);
    }
}
