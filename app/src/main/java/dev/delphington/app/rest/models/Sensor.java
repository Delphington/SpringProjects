package dev.delphington.app.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 character")
  //  @NotEmpty(message = "This field wasn't empty")
    private String name;

    @OneToMany(mappedBy = "sensor")
    @JsonIgnore
    private List<Measurement> measurementList;

    @Column(name = "created_at")
  //  @NotEmpty(message = "This field wasn't empty")
    @JsonIgnore
    private LocalDateTime createdAt;


    @Column(name = "modified_at")
   // @NotEmpty(message = "This field wasn't empty")
    @JsonIgnore
    private LocalDateTime modifiedAt;

    @Override
    public String toString() {
        return "Sensor{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", createdAt=" + createdAt +
               ", modifiedAt=" + modifiedAt +
               '}';
    }
}

//todo: на время поставить

//Migration

//Log