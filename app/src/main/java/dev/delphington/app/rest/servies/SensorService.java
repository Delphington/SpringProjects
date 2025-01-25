package dev.delphington.app.rest.servies;

import dev.delphington.app.rest.models.Sensor;
import dev.delphington.app.rest.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(enrich(sensor));
    }

    @Transactional(readOnly = true)
    public Optional<Sensor> findByOne(Sensor sensor) {
        return findByName(sensor.getName());
    }

    @Transactional(readOnly = true)
    public Optional<Sensor> findByName(final String name) {
        return sensorRepository.findByName(name);
    }

    private Sensor enrich(Sensor sensor) {
        sensor.setCreatedAt(LocalDateTime.now());
        sensor.setModifiedAt(LocalDateTime.now());
        return sensor;
    }
}
