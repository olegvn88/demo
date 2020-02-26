package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.UUID;

public class BaseResponse {
    private final String result;
    private final Integer code;

    private final List<Person> person;

    public BaseResponse(String result, Integer code) {
        this(null, null, result, code);
    }

    public BaseResponse(List<Person> person, String status, Integer statusCode) {
        this(person, null, status, statusCode);
    }

    public BaseResponse(List<Person> person, UUID id, String status, int statusCode) {
        this.result = status;
        this.code = statusCode;
        this.person = person;
    }

    public String getResult() {
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public List<Person> getPerson() {
        return person;
    }
}
