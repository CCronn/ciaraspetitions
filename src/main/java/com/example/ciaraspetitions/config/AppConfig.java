package com.example.ciaraspetitions.config;

import com.example.ciaraspetitions.repository.PetitionRepository;
import com.example.ciaraspetitions.service.DummyDataSeeder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DummyDataSeeder dummyDataSeeder(PetitionRepository petitionRepository) {
        return new DummyDataSeeder(petitionRepository);
    }

}
