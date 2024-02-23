package se.ju23.typespeeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.ju23.typespeeder.service.MessageBundle;

import java.util.Locale;
import java.util.Scanner;

@Configuration
public class ApplicationConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public MessageBundle messageBundle() {
        return new MessageBundle(Locale.getDefault());
    }

}
