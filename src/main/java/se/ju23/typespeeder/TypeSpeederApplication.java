package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import se.ju23.typespeeder.controller.GameController;
import se.ju23.typespeeder.repository.PlayerRepository;
import se.ju23.typespeeder.repository.UsernameRepository;
import se.ju23.typespeeder.service.*;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "se.ju23.typespeeder")
public class TypeSpeederApplication implements CommandLineRunner {

    @Autowired
    GameController gameController;

    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        IOService ioService = new SystemIO(new Scanner(System.in));
//        GameService gameService = new GameEngine();
//        AuthenticationService authService = new AuthenticationEngine(ioService);

//        GameController gameController = new GameController();

        gameController.run();
    }
}
