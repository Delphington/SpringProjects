package dev.delphington.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Not be Empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "year")
    private Integer year;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> bookList;


    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @Transient
    private long daysBetween;


    public long getDaysBetween() {
        daysBetween = ChronoUnit.DAYS.between(createdAt, LocalDate.now());
        return daysBetween;

    }


    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", year=" + year +
               ", bookListSize=" + bookList.size() +
               '}';
    }
}
