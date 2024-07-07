package dev.airtrip.air_trip.models;

import java.util.Map;

public class AirQualityDTO {

    private String cityName;
    private Map<String, Double> values;
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Map<String, Double> getValues() {
        return values;
    }
    public void setValues(Map<String, Double> values) {
        this.values = values;
    }

}
/*Un DTO (Data Transfer Object) es una clase que se utiliza para transferir datos entre diferentes capas
 de una aplicación, especialmente entre el backend y el frontend.

*Mapear datos de una entidad a un DTO (Data Transfer Object) tiene varias ventajas importantes:

Seguridad: Permite enviar solo los datos necesarios al frontend, evitando exponer información sensible o innecesaria.
Optimización: Reduce la cantidad de datos transferidos, mejorando el rendimiento de la aplicación.
Desacoplamiento: Separa la lógica de negocio de la presentación, facilitando el mantenimiento y la evolución del código.
Validación: Facilita la validación de datos antes de enviarlos al frontend, asegurando que solo se envíen datos correctos y completos */