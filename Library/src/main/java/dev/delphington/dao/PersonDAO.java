package dev.delphington.dao;

import dev.delphington.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

    public Person show(int id) {
        //Возвращаем список
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }


    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, year) VALUES (?,?)", person.getName(), person.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    public void update(int id, Person person) {
        System.out.println("FROM DAO UPDATE");
        jdbcTemplate.update("UPDATE person SET name = ?, year = ? WHERE id = ?",
                person.getName(), person.getYear(), id);

    }
}
