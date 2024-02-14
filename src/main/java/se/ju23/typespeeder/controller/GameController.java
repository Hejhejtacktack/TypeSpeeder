package se.ju23.typespeeder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.MenuService;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.service.*;

import java.util.Optional;

@Component
public class GameController {

    private GameService gameService;
    private IOService ioService;
    private MenuService menuService;
    private AuthenticationService authenticationService;
    private PlayerService playerService;
    private UserInterfaceService uiService;

    private Player currentPlayer;

    public GameController(GameService gameService, IOService ioService, MenuService menuService, AuthenticationService authenticationService, PlayerService playerService, UserInterfaceService uiService) {
        this.gameService = gameService;
        this.ioService = ioService;
        this.menuService = menuService;
        this.authenticationService = authenticationService;
        this.playerService = playerService;
        this.uiService = uiService;
    }

    public void run(){
        boolean run = true;

        initialize();

        Optional<Player> player = this.authenticationService.login();
        if (player.isPresent()) {
            this.currentPlayer = player.get();

            do {
                String choice = this.uiService.promptForInput(menuService.getMenuOptions());

                switch (choice) {
                    case "1" -> this.authenticationService.login();
                    case "2" -> this.playerService.changePlayerInfo(currentPlayer);
                    case "3" -> {}
                    case "4" -> {}
                    case "5" -> {}
                    case "6" -> {}
                    case "0" -> {}
                }
            } while (run);
        } else {
            // TODO login failed. view only some alt of menu.


        }


    }

    private void initialize(){
        ioService.println("\nSpelet går ut på att programmet ska skriva ut en text där slumpmässiga bokstäver\n" +
                "och/eller ord markeras i en viss färg som användaren ska skriva in korrekt, rätt ordning,\n" +
                "stor/liten bokstav och på så kort tid som möjligt.");
    }
}
