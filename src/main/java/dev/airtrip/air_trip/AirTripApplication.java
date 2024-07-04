package dev.airtrip.air_trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AirTripApplication {

	 @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();
    }

	public static void main(String[] args) {
		SpringApplication.run(AirTripApplication.class, args);
	}

}
