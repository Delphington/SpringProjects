package dev.delphington.app.rest.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SensorErrorResponse {
    private String message;
    private Long timestamp;
}
