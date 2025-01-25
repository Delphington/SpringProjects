package dev.delphington.app.rest.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeasurementErrorResponse {
    private String message;
    private Long timestamp;
}
