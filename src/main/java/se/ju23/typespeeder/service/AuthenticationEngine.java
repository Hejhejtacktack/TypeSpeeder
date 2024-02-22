package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.MessageBundle;
import se.ju23.typespeeder.exception.AuthenticationException;
import se.ju23.typespeeder.exception.ValidationException;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.model.Username;
import se.ju23.typespeeder.repository.PlayerRepository;
import se.ju23.typespeeder.repository.UsernameRepository;

import java.util.Locale;
import java.util.Optional;

@Service
public class AuthenticationEngine implements AuthenticationService {

    private PlayerRepository playerRepo;
    private UsernameRepository usernameRepo;
    private final UIService uiService;
    private final IOService ioService;
    MessageBundle messageBundle;
    private Optional<Player> currentPlayer = Optional.empty();

    @Autowired
    public AuthenticationEngine(PlayerRepository playerRepo, UsernameRepository usernameRepo, UIService uiService, IOService ioService, MessageBundle messageBundle) {
        this.playerRepo = playerRepo;
        this.usernameRepo = usernameRepo;
        this.uiService = uiService;
        this.ioService = ioService;
        this.messageBundle = messageBundle;
    }

    public void setCurrentPlayer(Optional<Player> currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Optional<Player> getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks the DB for a present Player. Does not specify where authentication fails.
     * @param givenUsername
     * @param givenPassword
     * @return true if Player is present in DB and givenPassword matches password, otherwise false.
     * @throws AuthenticationException If username does not exist in DB.
     */
    @Override
    public boolean authenticate(String givenUsername, String givenPassword) throws AuthenticationException {
        Optional<Username> username = usernameRepo.findByValue(givenUsername);
        if (username.isPresent()) {
            Optional<Player> player = playerRepo.findByUsername(username.get());
            if (player.isPresent()) {
                return player.get().getPassword().equals(givenPassword);
            }
        } else {
            throw new AuthenticationException("Username does not exist in DB");
        }
        return false;
    }

    @Override
    public void validate(String string) throws ValidationException {
        if (usernameRepo.existsByValue(string)) {
            throw new ValidationException("Username is taken");
        }
    }

    @Override
    public Optional<Player> login() {
        this.ioService.println(this.messageBundle.getMessage("login.header"));

        String givenUsername = this.uiService.promptForInput(this.messageBundle.getMessage("login.usernamePrompt") + "\n> ");

        String givenPassword = this.uiService.promptForInput(this.messageBundle.getMessage("login.passwordPrompt") + "\n> ");

        try {
            boolean authenticated = authenticate(givenUsername, givenPassword);
            if (authenticated) {
                Optional<Username> username = this.usernameRepo.findByValue(givenUsername);
                Optional<Player> player = playerRepo.findByUsername(username.get());
                setCurrentPlayer(player);
                this.ioService.println(this.messageBundle.getMessage("login.success") + player.get().getAccountName());
                return player;
            }
        } catch (AuthenticationException aE) {
            this.ioService.println(this.messageBundle.getMessage("login.fail") + aE);
        }
        return Optional.empty();
    }

    @Override
    public void logout() {
        this.currentPlayer = Optional.empty();
    }

    @Override
    public void isLoggedIn() throws AuthenticationException {
        if (this.currentPlayer.isEmpty()) {
            throw new AuthenticationException("Please login first");
        }
    }
}
