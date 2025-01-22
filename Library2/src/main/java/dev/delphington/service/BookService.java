package dev.delphington.service;

import dev.delphington.model.Book;
import dev.delphington.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional(readOnly = true)
    public List<Book> index() {
        System.out.println("FROM DAO");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class, id));
    }

    @Transactional
    public void update(int id, Book book) {
        Session session = sessionFactory.getCurrentSession();
        Book bookTemp = session.get(Book.class, id);

        bookTemp.setYear(book.getYear());
        bookTemp.setAuthor(book.getAuthor());
        bookTemp.setName(book.getName());
        bookTemp.setOwner(book.getOwner());
    }

    @Transactional(readOnly = true)
    public List<Book> getBook(int personId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, personId).getBookList();

    }

    @Transactional
    public void updateOwner(int bookId, int personId) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, personId);
        Book book = session.get(Book.class, bookId);
        book.setOwner(person);
        person.getBookList().add(book);
    }
}
