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
        String sql = String.format("%s'%s')", "INSERT INTO person (id, name) VALUES(uuid_generate_v4(),", name);
        jdbcTemplate.update(sql);
        return new BaseResponse("Success", 200);
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Person(personId, name);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public BaseResponse updatePersonById(UUID id, Person person) {
        String sql = String.format("%s'%s'%s'%s'", "UPDATE person SET name = ", person.getName(), " WHERE id=", id);
        jdbcTemplate.update(sql);
        return new BaseResponse("Success", 200);
    }
}
