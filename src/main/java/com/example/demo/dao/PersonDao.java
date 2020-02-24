package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    BaseResponse insertPerson(UUID id, Person person);

    default BaseResponse insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int deletePersonByName(String name);

    BaseResponse updatePersonById(UUID id, Person person);

    int updatePersonCountryById(UUID id, Person person);

    int updatePersonCountryByName(Person person);

    Optional<Person> selectPersonByName(String name);
}