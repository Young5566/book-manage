package com.Young.book.utils;

public enum ResultEnum {
    SUCCESS(200),
    SQL_ERROR(1001),
    NOT_FOUND(1002),
    PARAMS_ERROR(400),
    LOGIN_ERROR(1003),
    EXITED(1003),
    NO_TOKEN(1004),
    TOKEN_ERROR(1005);

    private int code;

    private ResultEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
