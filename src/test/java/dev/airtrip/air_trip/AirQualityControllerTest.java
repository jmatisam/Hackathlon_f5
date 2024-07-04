package dev.airtrip.air_trip;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.airtrip.air_trip.controller.AirQualityController;
import dev.airtrip.air_trip.models.AirQualityDTO;
import dev.airtrip.air_trip.service.AirQualityService;

@WebMvcTest(AirQualityController.class)
public class AirQualityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirQualityService airQualityService;

    @Test
    public void testGetAirQualityData() throws Exception {
        String city = "testCity";
        AirQualityDTO airQualityDTO = new AirQualityDTO();
        airQualityDTO.setCityName(city);
        
        Map<String, Double> values = new HashMap<>();
        values.put("PM2.5", 10.5);
        values.put("PM10", 20.3);
        airQualityDTO.setValues(values);

        when(airQualityService.fetchAndSaveAirQualityData(city)).thenReturn(airQualityDTO);

        mockMvc.perform(get("/api/airquality/{city}", city)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"cityName\":\"testCity\",\"values\":{\"PM2.5\":10.5,\"PM10\":20.3}}"))
                .andDo(print());
    }

    @Test
    public void testGetAirQualityData_InternalServerError() throws Exception {
        String city = "testCity";

        when(airQualityService.fetchAndSaveAirQualityData(city)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/airquality/{city}", city)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }
}