package se.ju23.typespeeder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.model.Username;
import se.ju23.typespeeder.service.AuthenticationService;
import se.ju23.typespeeder.service.GameEngine;
import se.ju23.typespeeder.service.GameService;
import se.ju23.typespeeder.service.IOService;

@Component
public class GameController implements CommandLineRunner {

    GameService gameService;
    IOService ioService;
    AuthenticationService authenticationService;

    @Autowired
    public GameController(GameService gameService, IOService ioService, AuthenticationService authenticationService) {
        this.gameService = gameService;
        this.ioService = ioService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void run(String... args) throws Exception {
        Player player = new Player("Riot", new Username("Hejhejtacktack"), "guest");
        ioService.print(player);
    }

    private void initialize(){

    }

    private String mainMenu() {
        return """
                Main Menu
                
                1. Login
                2. Create account
                0. Quit
                """;
    }
}
