package dev.delphington.service;

import dev.delphington.model.Book;
import dev.delphington.model.Person;
import dev.delphington.repository.BookRepository;
import dev.delphington.repository.PersonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    //Пусть на страницу будет 5 книг

    @Transactional(readOnly = true)
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> index(int numberPage, int booksPerPage) {
        return bookRepository.findAll(PageRequest.of(numberPage, booksPerPage)).getContent();
    }

    @Transactional(readOnly = true)
    public List<Book> index(int numberPage, int booksPerPage, String nameSort) {
        return bookRepository.findAll(PageRequest.of(numberPage, booksPerPage, Sort.by(nameSort))).getContent();
    }

    @Transactional(readOnly = true)
    public List<Book> index(String nameSort) {
        return bookRepository.findAll(Sort.by(nameSort));
    }

    @Transactional(readOnly = true)
    public Book show(int id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.removeById(id);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> getBook(int personId) {
        return personRepository.findById(personId).getBookList();
    }

    @Transactional
    public void updateOwner(int bookId, int personId) {
        Book b = bookRepository.findById(bookId);
        Person person = personRepository.findById(personId);
        person.setCreatedAt(LocalDate.now());
        b.setOwner(person);
        bookRepository.save(b);
    }


    public Optional<Book> findByName(final String name) {
        return bookRepository.findByName(name);
    }
}
