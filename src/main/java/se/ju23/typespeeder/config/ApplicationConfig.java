package se.ju23.typespeeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.ju23.typespeeder.service.AuthenticationEngine;
import se.ju23.typespeeder.service.GameEngine;
import se.ju23.typespeeder.service.IOService;
import se.ju23.typespeeder.service.SystemIO;

import java.util.Scanner;

@Configuration
public class ApplicationConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
