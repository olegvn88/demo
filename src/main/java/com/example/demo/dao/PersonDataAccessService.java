package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BaseResponse insertPerson(UUID id, Person person) {
        return new BaseResponse("Success", 200);
    }

    @Override
    public BaseResponse insertPerson(Person person) {
        String name = person.getName();
        String country = person.getCountry() != null ? person.getCountry() : "UA";
        String sql = String.format("%s'%s','%s')", "INSERT INTO person (id, name, country) VALUES(uuid_generate_v4(),", name, country);
        jdbcTemplate.update(sql);
        return new BaseResponse("Success", 200);
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

    @Override
    public int deletePersonById(UUID id) {
        String sql = String.format("%s'%s'", "DELETE from person where id = ", id);
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public int deletePersonByName(String name) {
        String sql = String.format("%s'%s'", "DELETE from person where name = ", name);
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public BaseResponse updatePersonById(UUID id, Person person) {
        String sql = "";
        if (person.getName() != null && person.getCountry() == null && person.getEmail() == null) {
            sql = String.format("%s'%s'%s'%s'", "UPDATE person SET name = ", person.getName(), " WHERE id=", id);
        } else if (person.getCountry() != null && person.getName() == null && person.getEmail() == null) {
            sql = String.format("%s'%s'%s'%s'", "UPDATE person SET country = ", person.getCountry(), " WHERE id=", id);
        } else if (person.getName() != null && person.getCountry() != null && person.getEmail() == null) {
            System.out.println("update >>>" + person.getName() + " - " + person.getCountry());
            sql = String.format("%s'%s', %s'%s'%s'%s'", "UPDATE person SET name = ", person.getName(), " country = ", person.getCountry(), " WHERE id=", id);
        } else if (person.getEmail() != null && person.getName() != null && person.getCountry() == null) {
            System.out.println("update >>>" + person.getEmail());
            sql = String.format("%s'%s', %s'%s'%s'%s'", "UPDATE person SET name = ", person.getName(), " email = ", person.getEmail(), " WHERE id=", id);
        }
        jdbcTemplate.update(sql);
        return new BaseResponse("Success", 200);
    }

    @Override
    public int updatePersonCountryById(UUID id, Person person) {
        String sql = String.format("%s'%s'%s'%s'", "UPDATE person SET country = ", person.getCountry(), " WHERE id=", id);
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public int updatePersonCountryByName(Person person) {
        String sql = String.format("%s'%s' %s's'", "UPDATE person SET country = ", person.getCountry(), "WHERE name = ", person.getName());
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public Optional<Person> selectPersonByName(String findName) {

        final String sql = "SELECT id, name, country, email FROM person WHERE name = ?";
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
//        return null;
    }
}