package dev.airtrip.air_trip.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.airtrip.air_trip.models.AirQualityPredictionDTO;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class AirQualityPredictionService {

    @Autowired
    private Dotenv dotenv;

    public AirQualityPredictionDTO fetchAirQualityPredictions(String city) throws Exception {
        String apiToken = dotenv.get("API_TOKEN");
        String apiUrlPattern = dotenv.get("API_URL_PATTERN");
        String apiUrl = String.format(apiUrlPattern, city, apiToken);

        // Realizar la solicitud GET y obtener el JSON como String
        RestTemplate restTemplate = new RestTemplate();
        String jsonData = restTemplate.getForObject(apiUrl, String.class);

        // Procesar el JSON y extraer las predicciones
        Map<String, Map<String, Integer>> predictions = parseAirQualityPredictions(jsonData);

        // Crear el DTO para enviar al frontend
        AirQualityPredictionDTO dto = new AirQualityPredictionDTO();
        dto.setCityName(city);
        dto.setPredictions(predictions);

        return dto;
    }

    private Map<String, Map<String, Integer>> parseAirQualityPredictions(String jsonData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonData);
        JsonNode dataNode = root.path("data");
        JsonNode forecastsNode = dataNode.path("forecast").path("daily");

        Map<String, Map<String, Integer>> predictions = new HashMap<>();

        forecastsNode.fieldNames().forEachRemaining(field -> {
            JsonNode dayArray = forecastsNode.path(field);
            if (dayArray.isArray()) {
                for (JsonNode dayNode : dayArray) {
                    String day = dayNode.path("day").asText();
                    Map<String, Integer> prediction = new HashMap<>();
                    prediction.put("avg", dayNode.path("avg").asInt());
                    prediction.put("max", dayNode.path("max").asInt());
                    prediction.put("min", dayNode.path("min").asInt());
                    if (!predictions.containsKey(day)) {
                        predictions.put(day, new HashMap<>());
                    }
                    predictions.get(day).put(field, prediction.get("avg"));
                }
            }
        });

        return predictions;
    }
}
