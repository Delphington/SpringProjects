package dev.delphington.service;

import dev.delphington.model.Person;
import dev.delphington.repository.PersonRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Getter
@Setter
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        return personRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Person> show(String name) {
        return Optional.of(personRepository.findByName(name));
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.removeById(id);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }
}
