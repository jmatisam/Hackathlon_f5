package dev.airtrip.air_trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.airtrip.air_trip.models.AirQualityDTO;
import dev.airtrip.air_trip.service.AirQualityService;



@RestController
@RequestMapping("/api/airquality")
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;

    @GetMapping("/{city}")
    public ResponseEntity<AirQualityDTO> getAirQualityData(@PathVariable String city) {
        try {
            AirQualityDTO airQualityDTO = airQualityService.fetchAndSaveAirQualityData(city);
            return ResponseEntity.ok(airQualityDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
