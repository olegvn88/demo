package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public BaseResponse insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName(), person.getCountry(), person.getEmail()));
        return new BaseResponse("Success", 200);//.getCode();
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
//        if (personMaybe.isEmpty()) {
//            return 0;
//        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public BaseResponse updatePersonById(UUID id, Person update) {
        selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if (indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(id, update.getName(), update.getCountry(), update.getEmail()));
                        return new BaseResponse("Failed", 400);
                    }
                    return new BaseResponse("Success", 200);
                })
                .orElse(new BaseResponse("Sucess", 200));
        return new BaseResponse("Success", 200);
    }

    @Override
    public Optional<Person> selectPersonByName(String name) {
        return Optional.empty();
    }

    @Override
    public int deletePersonByName(String name) {
        return 0;
    }
}
