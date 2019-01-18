package com.Young.book.utils;

public class Result<T> {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    public Result() {
        this(ResultEnum.SUCCESS, "success");
    }

    public Result(T data){
        this(ResultEnum.SUCCESS.getCode(), "success", data);
    }

    public Result(ResultEnum resultEnum, String message) {
        this(resultEnum.getCode(), message, null);
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
