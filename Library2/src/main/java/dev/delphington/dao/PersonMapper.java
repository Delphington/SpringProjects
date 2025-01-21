package dev.delphington.dao;


import dev.delphington.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId((int) rs.getLong("id"));
        person.setName(rs.getString("name"));
        person.setYear(rs.getInt("year"));
        return person;
    }
}
