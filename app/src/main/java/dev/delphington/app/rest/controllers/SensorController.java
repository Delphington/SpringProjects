package dev.delphington.app.rest.controllers;

import dev.delphington.app.rest.dto.SensorDTO;
import dev.delphington.app.rest.models.Sensor;
import dev.delphington.app.rest.servies.SensorService;
import dev.delphington.app.rest.util.response.SensorErrorResponse;
import dev.delphington.app.rest.util.exception.sensor.SensorAlreadyExistsException;
import dev.delphington.app.rest.util.exception.sensor.SensorNameValidationException;
import dev.delphington.app.rest.util.srv.ConvertUtils;
import dev.delphington.app.rest.util.srv.ErrorUtils;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ConvertUtils convertUtils;
    private final ErrorUtils errorUtils;

    @Autowired
    public SensorController(SensorService sensorService, ConvertUtils convertUtils, ErrorUtils errorUtils) {
        this.sensorService = sensorService;
        this.convertUtils = convertUtils;
        this.errorUtils = errorUtils;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String messageError = errorUtils.getErrors(bindingResult);
            throw new SensorNameValidationException(messageError);
        }


        Optional<Sensor> sensorOptional = sensorService.findByOne(convertUtils.convertToSensor(sensorDTO));
        if (sensorOptional.isPresent()) {
            throw new SensorAlreadyExistsException("This sensor already exists");
        }
        sensorService.save(convertUtils.convertToSensor(sensorDTO));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException
            (SensorNameValidationException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException
            (SensorAlreadyExistsException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
}