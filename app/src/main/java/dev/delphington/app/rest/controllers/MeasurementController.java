package dev.delphington.app.rest.controllers;

import dev.delphington.app.rest.dto.MeasurementDTO;
import dev.delphington.app.rest.models.Measurement;
import dev.delphington.app.rest.models.Sensor;
import dev.delphington.app.rest.servies.MeasurementService;
import dev.delphington.app.rest.servies.SensorService;
import dev.delphington.app.rest.util.exception.measurement.MeasurementFieldValidationException;
import dev.delphington.app.rest.util.exception.measurement.MeasurementSensorNotFoundException;
import dev.delphington.app.rest.util.response.MeasurementErrorResponse;
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
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final ErrorUtils errorUtils;

    @Autowired
    public MeasurementController(MeasurementService measurementService, SensorService sensorService, ModelMapper modelMapper, ErrorUtils errorUtils) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.errorUtils = errorUtils;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement
            (@RequestBody @Valid MeasurementDTO measurementDTO,
             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String messageError = errorUtils.getErrors(bindingResult);
            throw new MeasurementFieldValidationException(messageError);
        }

        Measurement measurement = convertToMeasurement(measurementDTO);

        Optional<Sensor> sensorOptional = sensorService.findByOne(measurement.getSensor());
        if (sensorOptional.isEmpty()) {
            throw new MeasurementSensorNotFoundException("This sensor is not found in the database");
        }

        measurement.setSensor(sensorOptional.get());
        measurementService.save(measurement);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementFieldValidationException e) {
        MeasurementErrorResponse mer = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(mer, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementSensorNotFoundException e) {
        MeasurementErrorResponse mer = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(mer, HttpStatus.BAD_REQUEST);
    }
}