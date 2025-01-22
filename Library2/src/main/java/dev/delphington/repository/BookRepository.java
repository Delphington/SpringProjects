package dev.delphington.repository;

import dev.delphington.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findById(int id);

    void removeById(int id);
}
