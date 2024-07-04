package dev.airtrip.air_trip.models;

import java.util.Map;

public class AirQualityPredictionDTO {

    private String cityName;
    private Map<String, Map<String, Integer>> predictions;

    // Getters y Setters
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Map<String, Map<String, Integer>> getPredictions() {
        return predictions;
    }

    public void setPredictions(Map<String, Map<String, Integer>> predictions) {
        this.predictions = predictions;
    }
}

