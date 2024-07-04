package dev.airtrip.air_trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.airtrip.air_trip.models.AirQualityPredictionDTO;
import dev.airtrip.air_trip.service.AirQualityPredictionService;

@RestController
@RequestMapping("/api")
public class AirQualityPredictionController {

    @Autowired
    private AirQualityPredictionService airQualityPredictionService;

    @GetMapping("/predictions/{city}")
    public ResponseEntity<AirQualityPredictionDTO> getAirQualityPredictions(@PathVariable String city) {
        try {
            AirQualityPredictionDTO airQualityPredictionDTO = airQualityPredictionService.fetchAirQualityPredictions(city);
            return ResponseEntity.ok(airQualityPredictionDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

