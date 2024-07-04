package dev.airtrip.air_trip;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import dev.airtrip.air_trip.models.AirQualityPredictionDTO;

public class AirQualityPredictionDTOTest {

    @Test
    public void testGettersAndSetters() {
        AirQualityPredictionDTO dto = new AirQualityPredictionDTO();

        String cityName = "TestCity";
        dto.setCityName(cityName);
        assertEquals(cityName, dto.getCityName());

        Map<String, Map<String, Integer>> predictions = new HashMap<>();
        Map<String, Integer> day1 = new HashMap<>();
        day1.put("avg", 50);
        day1.put("max", 100);
        day1.put("min", 25);
        predictions.put("2024-07-01", day1);

        dto.setPredictions(predictions);
        assertNotNull(dto.getPredictions());
        assertEquals(predictions, dto.getPredictions());
        assertEquals(day1, dto.getPredictions().get("2024-07-01"));
    }
}
