package com.project.trialmatching;

import com.project.trialmatching.model.entity.Hospital;
import com.project.trialmatching.repository.HospitalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.UUID;

@SpringBootApplication
@EnableMethodSecurity
public class EhrMatchingPlatformApplication {
	@Bean
	public CommandLineRunner seedHospitals(HospitalRepository hospitalRepository) {
		return args -> {
			if (!hospitalRepository.existsById(UUID.fromString("11111111-1111-1111-1111-111111111111"))) {
				hospitalRepository.save(Hospital.builder()
						.id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
						.name("Apollo Hospital")
						.address("Hyderabad, India")
						.build());
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(EhrMatchingPlatformApplication.class, args);
	}

}
