package dev.delphington.app.rest.repositories;

import dev.delphington.app.rest.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> getMeasurementByRainingIsTrue();
}
