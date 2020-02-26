package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.UUID;

public class BaseResponse {
    private final String result;
    private final Integer code;

    private final Person person;

    public BaseResponse(String result, Integer code) {
        this(null, null, result, code);
    }

    public BaseResponse(Person person, String status, Integer statusCode) {
        this(person, null, status, statusCode);
    }

    public BaseResponse(Person person, UUID id, String status, int statusCode) {
        this.result = status;
        this.code = statusCode;
        this.person = person;
        this.person.setId(id);
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
