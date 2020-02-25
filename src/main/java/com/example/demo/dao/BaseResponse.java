package com.example.demo.dao;

import com.example.demo.model.Person;

public class BaseResponse {
    private final String result;
    private final Integer code;

    private final Person person;

    public BaseResponse(String result, Integer code) {
        this(null, result, code);
    }

    public BaseResponse(Person person, String result, Integer code) {
        this.result = result;
        this.code = code;
        this.person = person;
    }

    public String getResult() {
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public Person getPerson() {
        return person;
    }
}
