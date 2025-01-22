package dev.delphington.service;

import dev.delphington.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Repository
public class PersonService {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional(readOnly = true)
    public Optional<Person> show(String name) {
        Session session = sessionFactory.getCurrentSession();

        Query<Person> query = session.createQuery(
                "SELECT p FROM Person p WHERE p.name = :name", Person.class); // Используем HQL и именованный параметр
        query.setParameter("name", name); // Устанавливаем значение параметра

        return query.uniqueResultOptional();
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    @Transactional
    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person p = session.get(Person.class, id);
        p.setName(person.getName());
        p.setYear(person.getYear());
    }
}
