package dev.delphington.app.rest.servies;

import dev.delphington.app.rest.models.Measurement;
import dev.delphington.app.rest.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurementRepository.save(enrich(measurement));
    }

    @Transactional(readOnly = true)
    public List<Measurement> getRainMeasurements() {
        return measurementRepository.getMeasurementByRainingIsTrue();
    }


    @Transactional(readOnly = true)
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }


    private Measurement enrich(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setModifiedAt(LocalDateTime.now());
        return measurement;
    }
}
