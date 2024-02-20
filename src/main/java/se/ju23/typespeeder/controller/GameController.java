package se.ju23.typespeeder.controller;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.exception.PlayException;
import se.ju23.typespeeder.model.NewsLetter;
import se.ju23.typespeeder.exception.AccountCreationException;
import se.ju23.typespeeder.exception.AuthenticationException;
import se.ju23.typespeeder.service.MenuService;
import se.ju23.typespeeder.model.LeaderboardView;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class GameController {

    private GameService gameService;
    private IOService ioService;
    private MenuService menuService;
    private AuthenticationService authenticationService;
    private PlayerService playerService;
    private UIService uiService;
    private AccountService accountService;
    private LeaderBoardService leaderBoardService;
    private NewsLetterService newsLetterService;

    private final Optional<Player> currentPlayer = Optional.empty();

    public GameController(GameService gameService, IOService ioService, MenuService menuService, AuthenticationService authenticationService, PlayerService playerService, UIService uiService, AccountService accountService, LeaderBoardService leaderBoardService, NewsLetterService newsLetterService) {
        this.gameService = gameService;
        this.ioService = ioService;
        this.menuService = menuService;
        this.authenticationService = authenticationService;
        this.playerService = playerService;
        this.uiService = uiService;
        this.accountService = accountService;
        this.leaderBoardService = leaderBoardService;
        this.newsLetterService = newsLetterService;
    }

    public void run(){
        boolean run = true;

        initialize();

        do {
            String choice = this.uiService.promptForInput(this.menuService.getMenuOptions());

            switch (choice) {
                case "1" -> this.login();
                case "2" -> this.createAccount();
                case "3" -> this.changePlayer();
                case "4" -> this.displayLeaderboard();
                case "5" -> this.displayNewsLetter();
                case "6" -> this.changeLanguage();
                case "7" -> this.play();
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

    private void login() {
        this.authenticationService.login();
    }

    private void createAccount() {
        try {
            this.accountService.build();
        } catch (AccountCreationException aCE) {
            this.ioService.println(aCE);
        }
    }

    private void changePlayer() {
        try {
            this.playerService.changePlayerInfo(this.currentPlayer);
        } catch (AuthenticationException aE) {
            ioService.println(aE);
        }
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

    private void displayNewsLetter() {
        Optional<NewsLetter> newsLetter = this.newsLetterService.getLatestNewsLetter();
        if (newsLetter.isPresent()) {
            this.ioService.print(newsLetter.get().toString());
        } else {
            this.ioService.println("No newsletter found!");
        }
    }

    private void changeLanguage() {

    }

    private void play() {
        boolean run = true;

        do {
            String mode = this.uiService.promptForInput("""
                    \nDo you want to play
                    1. Write sentences
                    2. Write characters
                    3. Write symbols
                    >\s""")
                    .trim();

            String difficulty = this.uiService.promptForInput("""
                    Which difficulty do you want?
                    1. Easy
                    2. Medium
                    3. Hard
                    >\s""")
                    .trim();

            try {
                this.gameService.play(mode, difficulty);
                run = false;
            } catch (PlayException pE) {
                this.ioService.print(pE);
            }
        } while (run);
//        this.updatePlayerStats();
//        Hej hopp
    }

    private void quit() {
        this.ioService.println("\nExiting program. Thank you for playing!");
        System.exit(0);
    }
}
