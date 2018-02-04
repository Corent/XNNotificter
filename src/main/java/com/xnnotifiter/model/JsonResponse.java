package com.xnnotifiter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class JsonResponse<T> {

    public static final Integer ERROR_CODE = 1;
    public static final Integer SUCCESS_CODE = 0;
    public static final String SUCCESS_MSG = "success";
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private int code;
    private String msg;

    public JsonResponse() { }

    public static <T> JsonResponse<T> success(T data) {
        JsonResponse<T> response = new JsonResponse();
        response.setData(data);
        response.setCode(SUCCESS_CODE);
        response.setMsg(SUCCESS_MSG);
        return response;
    }

    public static <T> JsonResponse<T> success() {
        JsonResponse<T> response = new JsonResponse();
        response.setCode(SUCCESS_CODE);
        response.setMsg(SUCCESS_MSG);
        return response;
    }

    public static <T> JsonResponse<T> success(String msg) {
        JsonResponse<T> response = new JsonResponse();
        response.setCode(SUCCESS_CODE);
        response.setMsg(SUCCESS_MSG);
        return response;
    }

    public static <T> JsonResponse<T> error(int code, String msg) {
        JsonResponse<T> response = new JsonResponse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> JsonResponse<T> error(String msg) {
        return error(ERROR_CODE, msg);
    }

    public static <T> JsonResponse<T> error(JsonResponse.IStatus status) {
        return error(status.getCode(), status.getMessage());
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public interface IStatus {
        Integer getCode();
        String getMessage();
    }
}
