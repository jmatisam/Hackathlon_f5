package dev.airtrip.air_trip.models;

import java.util.Map;

public class AirQualityDTO {

    private String cityName;
    private Map<String, Double> values;
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Map<String, Double> getValues() {
        return values;
    }
    public void setValues(Map<String, Double> values) {
        this.values = values;
    }

}
