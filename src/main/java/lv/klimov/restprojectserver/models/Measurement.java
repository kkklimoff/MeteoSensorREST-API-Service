package lv.klimov.restprojectserver.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "temp_value")
    @Min(value = -100, message = "Temperature can't be lower than -100")
    @Max(value = 100, message = "Temperature can't be higher than 100")
    private double value;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "time_of_measurement")
    private LocalDateTime time;

    public Measurement() {
    }

    public Measurement(double value, Boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                ", time=" + time +
                '}';
    }
}
