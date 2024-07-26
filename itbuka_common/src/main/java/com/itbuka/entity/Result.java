package com.itbuka.entity;

import lombok.Data;

@Data
public class Result<T> {

    private boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//返回消息

    private T data;//返回数据

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T)data;
    }
    public Result(String message) {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = message;
    }
    public Result(String message,T data) {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = message;
        this.data = data;
    }
    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "执行成功";
    }

    public static <T> Result<T> ok() {
        return new Result<>(true, StatusCode.OK, "操作成功");
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(true, StatusCode.OK, "操作成功",data);
    }

    public static <T> Result<T> ok(String msg) {
        return new Result<>(true, StatusCode.OK, msg);
    }

    public static <T> Result<T> ok(String msg, T data) {
        return new Result<>(true, StatusCode.OK, msg,data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(false, StatusCode.ERROR,"操作失败");
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(false, StatusCode.ERROR,msg);
    }

    public static <T>  Result<T> fail(T data) {
        return new Result<>(false, StatusCode.ERROR,"操作失败",data);
    }

    public static <T> Result<T> fail(String msg, T data) {
        return new Result<>(false, StatusCode.ERROR,msg,data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(false, code,msg,msg);
    }
    //getter and setter
}

