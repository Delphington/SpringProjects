package dev.delphington.app.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SensorDTO {
    @NotEmpty(message = "This field wasn't empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 character")
    private String name;
}
