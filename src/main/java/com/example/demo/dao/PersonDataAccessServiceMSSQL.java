package com.example.demo.dao;

import com.example.demo.model.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mssql")
public class PersonDataAccessServiceMSSQL implements PersonDao {
    final static Logger logger = Logger.getLogger(PersonDataAccessServiceMSSQL.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessServiceMSSQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BaseResponse insertPerson(UUID id, Person person) {
        System.out.println("insertPerson(UUID id, Person person)" + id);
        List<Person> personList = new ArrayList();
        personList.add(person);
        return new BaseResponse(personList, id, "Success", 200);
    }

    @Override
    public BaseResponse insertPerson(Person person) {
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        System.out.println("Base Response is called");
        String name = personList.get(0).getName();
        String country = person.getCountry() != null ? person.getCountry() : "UA";
        String sql = String.format("%s'%s','%s')", "INSERT INTO person (id, name, country) VALUES (NEWID(),", name, "");
        jdbcTemplate.update(sql);

//        return new BaseResponse(selectPersonByName(name).get(), "Success", 200);
        return new BaseResponse(personList, "Success", 200);
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name, country, email FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String country = resultSet.getString("country");
            String email = resultSet.getString("email");
            return new Person(id, name, country, email);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name, country, email FROM person WHERE id = ?";
        return getPerson(id, sql);
    }

    @Override
    public int deletePersonById(UUID id) {
        String sql = String.format("%s'%s'", "DELETE from person where id = ", id);
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public int deletePersonByName(String name) {
        String sql = String.format("%s'%s'", "DELETE FROM person WHERE name LIKE ", "%" + name + "%");
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public BaseResponse updatePersonById(UUID id, Person person) {
        String sql = "";

        if (person.getName() != null) {
            sql = String.format("%s'%s'%s'%s'", "UPDATE person SET name = ", person.getName(), " WHERE id=", id);
            System.out.println("update >>>" + person.getName());
        } else if (person.getCountry() != null) {
            sql = String.format("%s'%s'%s'%s'", "UPDATE person SET country = ", person.getCountry(), " WHERE id=", id);
            System.out.println("update >>>" + person.getCountry());
        } else if (person.getEmail() != null) {
            System.out.println("update >>>" + person.getEmail());
            sql = String.format("%s '%s' %s '%s'", "UPDATE person SET email =", person.getEmail(), " WHERE id =", id);
        }
        jdbcTemplate.update(sql);
        Person personData = selectPersonById(id).orElse(null);
        List<Person> personList = new ArrayList<>();
        personList.add(personData);
        logger.info(String.format("%s %s %s %s", personData.getName() + personData.getCountry() + personData.getEmail() + personData.getId()));
        return new BaseResponse(personList, "Success", 200);
    }

    @Override
    public Optional<Person> selectPersonByName(String findName) {

        final String sql = "SELECT id, name, country, email FROM person WHERE name = ?";
        return getPerson(findName, sql);
    }

    private Optional<Person> getPerson(String findName, String sql) {
        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{findName},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String country = resultSet.getString("country");
                    String email = resultSet.getString("email");
                    return new Person(personId, name, country, email);
                });
        return Optional.ofNullable(person);
    }

    private Optional<Person> getPerson(UUID id, String sql) {
        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String country = resultSet.getString("country");
                    String email = resultSet.getString("email");
                    return new Person(personId, name, country, email);
                });
        return Optional.ofNullable(person);
    }
}