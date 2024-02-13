package se.ju23.typespeeder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.service.AuthenticationService;
import se.ju23.typespeeder.service.GameService;
import se.ju23.typespeeder.service.IOService;

import java.util.Optional;

@Component
public class GameController {


    private GameService gameService;

    private IOService ioService;

    private AuthenticationService authenticationService;

    @Autowired
    public GameController(GameService gameService, IOService ioService, AuthenticationService authenticationService) {
        this.gameService = gameService;
        this.ioService = ioService;
        this.authenticationService = authenticationService;
    }

    public void run(){
        initialize();

        Optional<Player> player = authenticationService.login();
        if (player.isPresent()) {
            gameService.play();
        } else {
            ioService.println("Player not present");
        }
    }

    private void initialize(){
        ioService.println("\nSpelet går ut på att programmet ska skriva ut en text där slumpmässiga bokstäver\n" +
                "och/eller ord markeras i en viss färg som användaren ska skriva in korrekt, rätt ordning,\n" +
                "stor/liten bokstav och på så kort tid som möjligt.");
    }
}
