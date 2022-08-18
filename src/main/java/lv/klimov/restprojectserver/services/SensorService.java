package lv.klimov.restprojectserver.services;

import lv.klimov.restprojectserver.models.Sensor;
import lv.klimov.restprojectserver.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor){
        sensor.setRegisteredAt(LocalDateTime.now());
        sensor.setLastUpdatedAt(LocalDateTime.now());
        sensorRepository.save(sensor);
    }
}
