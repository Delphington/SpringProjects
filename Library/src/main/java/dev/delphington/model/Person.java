package dev.delphington.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "Not be Empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age schould be greater than 0")
    private Integer year;

    public Person(Integer id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

}
