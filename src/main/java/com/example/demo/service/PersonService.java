package com.example.demo.service;

import com.example.demo.dao.BaseResponse;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public BaseResponse addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePersonById(UUID id) {
        return personDao.deletePersonById(id);
    }

    public BaseResponse updatePersion(UUID id, Person person) {
        return personDao.updatePersonById(id, person);
    }

    public int deletePersonByName(String name) {
        return personDao.deletePersonByName(name);
    }

    public int updatePersonCountryByName(Person person) {
        return personDao.updatePersonCountryByName(person);
    }

    public Optional<Person> getPersonByName(String name) {
        return personDao.selectPersonByName(name);
    }
}
