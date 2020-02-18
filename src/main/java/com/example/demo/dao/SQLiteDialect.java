package com.example.demo.dao;

import com.example.demo.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SQLiteDialect implements PersonDao  {

    final static Logger logger = LoggerFactory.getLogger(SQLiteDialect.class);
    @Override
    public BaseResponse insertPerson(UUID id, Person person) {
        return new BaseResponse("Success", 200);//.getCode();
    }

    @Override
    public BaseResponse insertPerson(Person person) {
        return new BaseResponse("Success", 200);//.getCode();
    }

    @Override
    public List<Person> selectAllPeople() {
        return null;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public BaseResponse updatePersonById(UUID id, Person person) {
        return null;
    }

    @Override
    public int updatePersonCountryById(UUID id, Person person) {
        return 0;
    }
}