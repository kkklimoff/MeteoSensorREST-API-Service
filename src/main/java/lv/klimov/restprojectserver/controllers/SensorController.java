package lv.klimov.restprojectserver.controllers;

import lv.klimov.restprojectserver.dto.SensorDTO;
import lv.klimov.restprojectserver.models.Sensor;
import lv.klimov.restprojectserver.services.SensorService;
import lv.klimov.restprojectserver.util.ErrorUtil;
import lv.klimov.restprojectserver.util.SensorMeasurementErrorResponse;
import lv.klimov.restprojectserver.util.SensorMeasurementException;
import lv.klimov.restprojectserver.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                     BindingResult bindingResult){
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()){
            ErrorUtil.buildErrors(bindingResult);
        }

        sensorService.save(modelMapper.map(sensor, Sensor.class));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorMeasurementErrorResponse> handleException(SensorMeasurementException e){
        SensorMeasurementErrorResponse response = new SensorMeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
