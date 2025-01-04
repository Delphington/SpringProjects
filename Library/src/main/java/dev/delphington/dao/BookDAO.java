package dev.delphington.dao;

import dev.delphington.model.Book;
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

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (person_id,name, author, year) VALUES(?,?,?,?)", 0,
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name = ?, author = ?, year = ? WHERE id = ?",
                book.getName(), book.getAuthor(), book.getYear(), id);
    }
    //------------------------------------------------

    public List<Book> getBook(int personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{personId}, new BookMapper());

    }

    public void updateOwner(int bookId, int personId) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id = ?", personId, bookId);
    }


}
