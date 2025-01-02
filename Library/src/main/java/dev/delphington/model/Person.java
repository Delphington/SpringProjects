package dev.delphington.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Person {
    private Integer id;
    private String FIO;
    private Integer year;

    public Person(Integer id, String name) {
        this.id = id;
        FIO = name;
    }
}
