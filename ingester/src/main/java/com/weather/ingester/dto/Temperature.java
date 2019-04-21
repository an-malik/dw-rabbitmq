package com.weather.ingester.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Temperature {

    private long id;

    @Length(max = 32)
    private String city;

    @Length(max = 32)
    private float temperature;

    private Date observedTime;

    private Double latitude;

    private Double longitude;

    private float max;

    private float min;

    @Length(max = 1)
    private String unit = "F";

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public float getTemperature() {
        return temperature;
    }

    public Date getObservedTime() {
        return observedTime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
