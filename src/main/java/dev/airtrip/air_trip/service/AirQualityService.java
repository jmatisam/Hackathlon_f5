package dev.airtrip.air_trip.service;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        Optional<AirQualityEntity> existingEntity = airQualityRepository.findByCityName(city);

        AirQualityEntity entity;
        if (existingEntity.isPresent()) {
            // Actualizar los valores de la entrada existente
            entity = existingEntity.get();
            entity.setValues(values);
        } else {
            // Crear una nueva entrada
            entity = new AirQualityEntity();
            entity.setCityName(city);
            entity.setValues(values);
        }

        airQualityRepository.save(entity);

        // Crear el DTO para enviar al frontend
        AirQualityDTO dto = new AirQualityDTO();
        dto.setCityName(city);
        dto.setValues(values);

        return dto;
    }

    private Map<String, Double> parseAirQualityJson(String jsonData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonData);
        JsonNode dataNode = root.path("data");
        JsonNode iaqiNode = dataNode.path("iaqi");

        Map<String, Double> values = new HashMap<>();

        if (dataNode.isObject()) {
            dataNode.fieldNames().forEachRemaining(field -> {
                JsonNode valueNode = dataNode.path("aqi");
                if (valueNode.isNumber()) {
                    values.put(field, valueNode.doubleValue());
                }
            });

        }
        if (iaqiNode.isObject()) {
            iaqiNode.fieldNames().forEachRemaining(field -> {
                JsonNode valueNode = iaqiNode.path(field).path("v");
                if (valueNode.isNumber()) {
                    values.put(field, valueNode.doubleValue());
                }
            });
        }

        return values;
    }
    
}
