package dev.airtrip.air_trip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.airtrip.air_trip.models.AirQualityEntity;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQualityEntity, Long> {
    Optional<AirQualityEntity> findByCityName(String cityName);
}