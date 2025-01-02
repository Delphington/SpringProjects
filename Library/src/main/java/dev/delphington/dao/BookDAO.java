package dev.delphington.dao;

import dev.delphington.model.Book;
import dev.delphington.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate = null;

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }
}
