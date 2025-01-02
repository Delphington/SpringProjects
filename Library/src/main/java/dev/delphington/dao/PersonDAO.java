package dev.delphington.dao;

import dev.delphington.config.Config;
import dev.delphington.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class PersonDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate = null;

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());

    }

    public void save(Person person) {
        System.out.println("Person SAVE = " + person);
        jdbcTemplate.update("INSERT INTO person (name, year) VALUES (?,?)", person.getName(), person.getYear());
    }
}
