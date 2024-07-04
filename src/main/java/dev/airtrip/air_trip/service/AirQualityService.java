package dev.airtrip.air_trip.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import dev.airtrip.air_trip.models.AirQualityDTO;
import dev.airtrip.air_trip.models.AirQualityEntity;
import dev.airtrip.air_trip.repository.AirQualityRepository;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class AirQualityService {

    @Autowired
    private AirQualityRepository airQualityRepository;

    @Autowired
    private Dotenv dotenv;

    @Transactional
    public AirQualityDTO fetchAndSaveAirQualityData(String city) throws Exception {
        String apiToken = dotenv.get("API_TOKEN");
        String apiUrlPattern = dotenv.get("API_URL_PATTERN");
        String apiUrl = String.format(apiUrlPattern, city, apiToken);

        // Realizar la solicitud GET y obtener el JSON como String
        RestTemplate restTemplate = new RestTemplate();
        String jsonData = restTemplate.getForObject(apiUrl, String.class);

        // Procesar el JSON y extraer los datos necesarios
        Map<String, Double> values = parseAirQualityJson(jsonData);

        // Guardar en la base de datos
        AirQualityEntity entity = new AirQualityEntity();
        entity.setCityName(city);
        entity.setValues(values);
        airQualityRepository.save(entity);

        // Crear el DTO para enviar al frontend
        AirQualityDTO dto = new AirQualityDTO();
        dto.setCityName(city);
        dto.setValues(values);

        return dto;
    }

    private Map<String, Double> parseAirQualityJson(String jsonData) {
        // Implementa aquí la lógica para parsear el JSON y extraer los valores necesarios
        // Aquí un ejemplo básico, ajusta según la estructura real del JSON recibido
        // Considera usar librerías como Jackson para parseo JSON más robusto
        Map<String, Double> values = new HashMap<>();
        values.put("aqi", 119.0);
        values.put("co", 5.5);
        values.put("h", 65.0);
        values.put("no2", 10.1);
        values.put("o3", 57.8);
        values.put("p", 1004.0);
        values.put("pm10", 58.0);
        values.put("pm25", 119.0);
        values.put("so2", 1.6);
        values.put("t", 27.0);
        values.put("w", 3.6);

        return values;
    }
}
