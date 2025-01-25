package dev.delphington.app.rest.controllers;

import dev.delphington.app.rest.dto.SensorDTO;
import dev.delphington.app.rest.models.Sensor;
import dev.delphington.app.rest.servies.SensorService;
import dev.delphington.app.rest.util.SensorErrorResponse;
import dev.delphington.app.rest.util.exception.SensorAlreadyExistsException;
import dev.delphington.app.rest.util.exception.SensorNameValidationException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensors")
public class SensorControllers {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorControllers(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/capy")
    public String getGe(){
        return "CAPYBARA";
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String messageError = getErrors(bindingResult);
            throw new SensorNameValidationException(messageError);
        }

        Optional<Sensor> sensorOptional = sensorService.findByOne(convertToSensor(sensorDTO));
        if (sensorOptional.isPresent()) {
            throw new SensorAlreadyExistsException("This sensor already exists");
        }

        sensorService.save(convertToSensor(sensorDTO));

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
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


    private String getErrors(BindingResult bindingResult) {
        StringBuilder messageError = new StringBuilder();
        List<FieldError> listErrors = bindingResult.getFieldErrors();
        for (FieldError error : listErrors) {
            messageError.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return messageError.toString();
    }

}
