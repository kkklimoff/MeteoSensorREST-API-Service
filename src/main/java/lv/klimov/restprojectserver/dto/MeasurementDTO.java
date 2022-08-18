package lv.klimov.restprojectserver.dto;

import lv.klimov.restprojectserver.models.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @Min(value = -100, message = "Temperature can't be lower than -100")
    @Max(value = 100, message = "Temperature can't be higher than 100")
    private double value;

    @NotNull
    private Boolean raining;

    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
