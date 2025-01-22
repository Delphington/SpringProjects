package dev.delphington.repository;

import dev.delphington.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findById(int id);

    Person findByName(String name);

    void removeById(int id);

}
