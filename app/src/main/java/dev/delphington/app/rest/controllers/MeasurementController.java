package dev.delphington.app.rest.controllers;

import dev.delphington.app.rest.dto.MeasurementDTO;
import dev.delphington.app.rest.models.Measurement;
import dev.delphington.app.rest.models.Sensor;
import dev.delphington.app.rest.servies.MeasurementService;
import dev.delphington.app.rest.servies.SensorService;
import dev.delphington.app.rest.util.exception.measurement.MeasurementFieldValidationException;
import dev.delphington.app.rest.util.exception.measurement.MeasurementSensorNotFoundException;
import dev.delphington.app.rest.util.response.MeasurementErrorResponse;
import dev.delphington.app.rest.util.srv.ConvertUtils;
import dev.delphington.app.rest.util.srv.ErrorUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final ConvertUtils convertUtils;
    private final ErrorUtils errorUtils;

    @Autowired
    public MeasurementController(MeasurementService measurementService, SensorService sensorService, ConvertUtils convertUtils, ErrorUtils errorUtils) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.convertUtils = convertUtils;
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

        Measurement measurement = convertUtils.convertToMeasurement(measurementDTO);

        Optional<Sensor> sensorOptional = sensorService.findByOne(measurement.getSensor());
        if (sensorOptional.isEmpty()) {
            throw new MeasurementSensorNotFoundException("This sensor is not found in the database");
        }

        measurement.setSensor(sensorOptional.get());
        measurementService.save(measurement);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return convertUtils.convertToListMeasurementDTO(measurementService.findAll());
    }


    @GetMapping("/rainyDaysCount")
    public List<MeasurementDTO> getRainMeasurements(){
        return convertUtils.convertToListMeasurementDTO(measurementService.getRainMeasurements());
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