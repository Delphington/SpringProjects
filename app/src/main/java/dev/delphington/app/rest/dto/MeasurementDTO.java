package dev.delphington.app.rest.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.delphington.app.rest.models.Sensor;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
@ToString
public class MeasurementDTO {

    @NotNull(message = "Value cannot be null")
    @Min(value = -100, message = "Value must be greater than or equal to -100")
    @Max(value = 100, message = "Value must be less than or equal to 100")
    private Double value;

    @NotNull(message = "Raining cannot be null")
    private Boolean raining;

    @NotNull(message = "Sensor cannot be null")
    @JsonManagedReference
    private SensorDTO sensorDTO;
}
