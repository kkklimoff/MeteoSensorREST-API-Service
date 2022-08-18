package lv.klimov.restprojectserver.services;

import lv.klimov.restprojectserver.models.Measurement;
import lv.klimov.restprojectserver.repositories.MeasurementRepository;
import lv.klimov.restprojectserver.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final SensorRepository sensorRepository;

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(SensorRepository sensorRepository, MeasurementRepository measurementRepository) {
        this.sensorRepository = sensorRepository;
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement){
        measurement.setTime(LocalDateTime.now());
        measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).get());
        measurementRepository.save(measurement);
    }
}
