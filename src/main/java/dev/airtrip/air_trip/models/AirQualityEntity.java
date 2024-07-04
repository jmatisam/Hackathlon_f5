package dev.airtrip.air_trip.models;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "air_quality")
@Data
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor 

public class AirQualityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cityName;

    @ElementCollection
    @CollectionTable(name = "air_quality_values", joinColumns = @JoinColumn(name = "air_quality_id"))
    @MapKeyColumn(name = "parameter")
    @Column(name = "value")
    private Map<String, Double> values;

}
