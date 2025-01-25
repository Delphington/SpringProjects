package dev.delphington.app.rest.controllers;

import dev.delphington.app.rest.dto.MeasurementDTO;
import dev.delphington.app.rest.models.Measurement;
import dev.delphington.app.rest.servies.MeasurementService;
import dev.delphington.app.rest.util.exception.measurement.MeasurementFieldValidationException;
import dev.delphington.app.rest.util.response.MeasurementErrorResponse;
import dev.delphington.app.rest.util.srv.ErrorUtils;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {


    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final ErrorUtils errorUtils;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, ErrorUtils errorUtils) {
        this.measurementService = measurementService;
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

        System.out.println(measurement);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementFieldValidationException e) {
        MeasurementErrorResponse mer = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(mer, HttpStatus.BAD_REQUEST);
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
