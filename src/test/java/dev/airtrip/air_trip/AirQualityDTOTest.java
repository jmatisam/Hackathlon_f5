package dev.airtrip.air_trip;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.airtrip.air_trip.models.AirQualityDTO;

public class AirQualityDTOTest {

    private AirQualityDTO airQualityDTO;

    @BeforeEach
    public void setUp() {
        airQualityDTO = new AirQualityDTO();
    }

    @Test
    public void testGettersAndSetters() {
        String cityName = "New York";
        Map<String, Double> values = new HashMap<>();
        values.put("PM2.5", 12.3);
        values.put("PM10", 45.6);

        airQualityDTO.setCityName(cityName);
        airQualityDTO.setValues(values);

        assertEquals(cityName, airQualityDTO.getCityName());
        assertEquals(values, airQualityDTO.getValues());
    }

    @Test
    public void testNoArgsConstructor() {
        AirQualityDTO dto = new AirQualityDTO();
        assertNotNull(dto);
    }
}
