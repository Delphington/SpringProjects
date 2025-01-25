package dev.delphington.app.rest.util.srv;

import dev.delphington.app.rest.dto.MeasurementDTO;
import dev.delphington.app.rest.dto.SensorDTO;
import dev.delphington.app.rest.models.Measurement;
import dev.delphington.app.rest.models.Sensor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertUtils {

    private final ModelMapper modelMapper;

    @Autowired
    public ConvertUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
