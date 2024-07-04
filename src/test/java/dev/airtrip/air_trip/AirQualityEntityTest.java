package dev.airtrip.air_trip;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.airtrip.air_trip.models.AirQualityEntity;

public class AirQualityEntityTest {

    private AirQualityEntity airQualityEntity;

    @BeforeEach
    public void setUp() {
        airQualityEntity = new AirQualityEntity();
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String cityName = "New York";
        Map<String, Double> values = new HashMap<>();
        values.put("PM2.5", 12.3);
        values.put("PM10", 45.6);

        airQualityEntity.setId(id);
        airQualityEntity.setCityName(cityName);
        airQualityEntity.setValues(values);

        assertEquals(id, airQualityEntity.getId());
        assertEquals(cityName, airQualityEntity.getCityName());
        assertEquals(values, airQualityEntity.getValues());
    }

    @Test
    public void testNoArgsConstructor() {
        AirQualityEntity entity = new AirQualityEntity();
        assertNotNull(entity);
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String cityName = "Los Angeles";
        Map<String, Double> values = new HashMap<>();
        values.put("CO", 0.9);
        values.put("NO2", 20.1);

        AirQualityEntity entity = new AirQualityEntity(id, cityName, values);

        assertEquals(id, entity.getId());
        assertEquals(cityName, entity.getCityName());
        assertEquals(values, entity.getValues());
    }
}