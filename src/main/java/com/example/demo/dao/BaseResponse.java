package com.example.demo.dao;

public class BaseResponse {
    private final String name;
    private final Integer code;

    public BaseResponse(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
}
