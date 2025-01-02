package dev.delphington.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Person {
    private Integer id;
    private String name;
    private Integer year;

    public Person(Integer id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}
