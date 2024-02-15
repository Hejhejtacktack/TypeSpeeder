package se.ju23.typespeeder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.AuthenticationException;
import se.ju23.typespeeder.MenuService;
import se.ju23.typespeeder.model.LeaderboardView;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.service.*;

import java.util.List;
import java.util.Optional;

@Component
public class GameController {

    private GameService gameService;
    private IOService ioService;
    private MenuService menuService;
    private AuthenticationService authenticationService;
    private PlayerService playerService;
    private UserInterfaceService uiService;

    private LeaderBoardService leaderBoardService;

    private final Optional<Player> currentPlayer = Optional.empty();

    public GameController(GameService gameService,
                          IOService ioService,
                          MenuService menuService,
                          AuthenticationService authenticationService,
                          PlayerService playerService,
                          UserInterfaceService uiService,
                          LeaderBoardService leaderBoardService) {
        this.gameService = gameService;
        this.ioService = ioService;
        this.menuService = menuService;
        this.authenticationService = authenticationService;
        this.playerService = playerService;
        this.uiService = uiService;
        this.leaderBoardService = leaderBoardService;
    }

    public void run(){
        boolean run = true;

        initialize();

        do {
            String choice = this.uiService.promptForInput(this.menuService.getMenuOptions());

            switch (choice) {
                case "1" -> this.authenticationService.login();
                case "2" -> {
                    try {
                        this.playerService.changePlayerInfo(this.currentPlayer);
                    } catch (AuthenticationException aE) {
                        ioService.println(aE);
                    }
                }
                case "3" -> this.displayLeaderboard();
//              case "4" -> { PATCH NOTES }
//              case "5" -> { PLAY }
                case "0" -> this.quit();
                default -> this.ioService.println("Error: Please enter a menu option");
            }
        } while (run);


    }

    private void initialize() {
        ioService.println("\nSpelet går ut på att programmet ska skriva ut en text där slumpmässiga bokstäver\n" +
                "och/eller ord markeras i en viss färg som användaren ska skriva in korrekt, rätt ordning,\n" +
                "stor/liten bokstav och på så kort tid som möjligt.");
    }

    private void displayLeaderboard() {
        List<LeaderboardView> leaderboard = this.leaderBoardService.getLeaderboard();

        if (!leaderboard.isEmpty()) {
            this.ioService.println("\n\t\tLEADERBOARD");
            this.ioService.println(String.format("%-17s%-17s%-17s", "Account name", "Score", "Level"));

            for (LeaderboardView entry : leaderboard) {
                this.ioService.println(String.format("%-16s %-16.2f %-16d",
                        entry.getAccountName(), entry.getScore(), entry.getLevel()));
            }
            this.ioService.println();
        } else {
            ioService.println("\nLeaderboard is empty.");
        }
    }

    private void quit(){
        this.ioService.println("\nExiting program. Thank you for playing!");
        System.exit(0);
    }
}
