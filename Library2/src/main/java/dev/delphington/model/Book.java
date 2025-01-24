package dev.delphington.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne
    private Person owner;

    @NotEmpty(message = "cannot be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "cannot be empty")
    @Size(min = 2, max = 30, message = "Author should be between 2 and 30 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "year")
    private Integer year;

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", author='" + author + '\'' +
               ", year=" + year +
               '}';
    }
}