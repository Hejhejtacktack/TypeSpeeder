package se.ju23.typespeeder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.MessageBundle;
import se.ju23.typespeeder.exception.PlayException;
import se.ju23.typespeeder.model.NewsLetter;
import se.ju23.typespeeder.exception.AccountCreationException;
import se.ju23.typespeeder.exception.AuthenticationException;
import se.ju23.typespeeder.service.MenuService;
import se.ju23.typespeeder.model.LeaderboardView;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.service.*;

import java.util.List;
import java.util.Locale;
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
    private MessageBundle messageBundle;
    private LocaleServiceImpl localeService;

    @Autowired
    public GameController(GameService gameService, IOService ioService, MenuService menuService, AuthenticationService authenticationService, PlayerService playerService, UIService uiService, AccountService accountService, LeaderBoardService leaderBoardService, NewsLetterService newsLetterService, MessageBundle messageBundle, LocaleServiceImpl localeService) {
        this.gameService = gameService;
        this.ioService = ioService;
        this.menuService = menuService;
        this.authenticationService = authenticationService;
        this.playerService = playerService;
        this.uiService = uiService;
        this.accountService = accountService;
        this.leaderBoardService = leaderBoardService;
        this.newsLetterService = newsLetterService;
        this.messageBundle = messageBundle;
        this.localeService = localeService;
    }

    public void run(){
        boolean run = true;
        boolean loggedIn = false;

        initialize();

        do {
            this.ioService.println(this.messageBundle.getMessage("menu.startHeader"));
            this.menuService.displayMenu(this.menuService.getMenuOptions(this.menuService.startMenu()));
            String choice = this.uiService.promptForInput("> ");

            switch (choice) {
                case "1" -> loggedIn = this.login();
                case "2" -> this.createAccount();
                case "3" -> this.changeLanguage();
                case "4" -> this.displayNewsLetter();
                case "0" -> this.quit();
                default -> this.printErrorMessage();
            }

            while (loggedIn) {
                this.ioService.println(this.messageBundle.getMessage("menu.mainHeader"));
                this.menuService.displayMenu(this.menuService.getMenuOptions(this.menuService.mainMenu()));
                choice = choice = this.uiService.promptForInput("> ");

                switch (choice) {
                    case "1" -> this.play();
                    case "2" -> this.displayLeaderboard();
                    case "3" -> this.changePlayerInfo();
                    case "4" -> this.displayNewsLetter();
                    case "0" -> {
                        this.logout();
                        loggedIn = false;
                    }
                    default -> this.printErrorMessage();
                }
            }
        } while (run);
    }

    private void initialize() {
        this.localeService.setCurrentLocale(Locale.ENGLISH);
        ioService.println(this.messageBundle.getMessage("initialize.message"));
    }

    private void printErrorMessage() {
        this.ioService.println(this.messageBundle.getMessage("menu.error"));
    }

    private boolean login() {
        boolean loggedIn = this.authenticationService.login().isPresent();
        if (loggedIn) {
            String accountName = this.authenticationService.getCurrentPlayer().get().getAccountName();
            return true;
        } else {
            return false;
        }
    }

    private void createAccount() {
        try {
            this.accountService.build();
        } catch (AccountCreationException aCE) {
            this.ioService.println(aCE);
        }

        this.ioService.println(this.messageBundle.getMessage("account.creationSuccess"));
    }

    private void changePlayerInfo() {
        try {
            this.authenticationService.isLoggedIn();
            this.playerService.changePlayerInfo(this.authenticationService.getCurrentPlayer());
        } catch (AuthenticationException aE) {
            this.ioService.println(aE);
        }
    }

    private void displayLeaderboard() {
        List<LeaderboardView> leaderboard = this.leaderBoardService.getLeaderboard();

        if (!leaderboard.isEmpty()) {
            this.ioService.println(this.messageBundle.getMessage("leaderboard.header"));
            this.ioService.println(String.format("%-17s%-17s%-17s",
                    this.messageBundle.getMessage("leaderboard.accountName"),
                    this.messageBundle.getMessage("leaderboard.score"),
                    this.messageBundle.getMessage("leaderboard.level")));

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
        String choice = this.uiService.promptForInput(this.messageBundle.getMessage("language.change") + "\n> ");

        switch (choice) {
            case "1" -> this.localeService.setCurrentLocale(new Locale("sv", "SE"));
            case "2" -> this.localeService.setCurrentLocale(Locale.ENGLISH);
            default -> this.ioService.println(this.messageBundle.getMessage("menu.error"));
        }
        this.ioService.println(this.messageBundle.getCurrentLocale());
        this.ioService.println(this.messageBundle.getMessage("language.selected"));
    }

    private void play() {
        try {
            this.authenticationService.isLoggedIn();
        } catch (AuthenticationException aE) {
            this.ioService.println(aE);
            return;
        }

        boolean run = true;
        double score = 0;

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
                score = this.gameService.play(mode, difficulty);
                run = false;
            } catch (PlayException pE) {
                this.ioService.print(pE);
            }
        } while (run);

        this.ioService.println("\nGood job!\n" +
                "This challenge score was: " + score);

        this.updateScore(score);
    }

    private void logout() {
        this.authenticationService.logout();
    }

    private void quit() {
        this.ioService.println("\nExiting program. Thank you for playing!");
        System.exit(0);
    }

    private void updateScore(double score) {
        Optional<Player> player = this.authenticationService.getCurrentPlayer();

        if (player.isPresent()) {
            Player playerToUpdate = player.get();
            this.ioService.print("\nUpdating score... ");
            if (this.playerService.updateScore(playerToUpdate, score)) {
                ceaseExecution();
                if (this.playerService.levelUp(playerToUpdate)) {
                    this.ioService.print("Congratulations! You've leveled up! You are now level " + playerToUpdate.getLevel() + "! ");
                }
                this.ioService.println("Done!");
            } else {
                this.ioService.println("Nothing to update!");
            }
        }
    }

    private void ceaseExecution() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
