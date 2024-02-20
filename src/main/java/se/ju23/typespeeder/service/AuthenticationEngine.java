package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.exception.AuthenticationException;
import se.ju23.typespeeder.exception.ValidationException;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.model.Username;
import se.ju23.typespeeder.repository.PlayerRepository;
import se.ju23.typespeeder.repository.UsernameRepository;

import java.util.Optional;

@Service
public class AuthenticationEngine implements AuthenticationService {

    private PlayerRepository playerRepo;
    private UsernameRepository usernameRepo;
    private final UIService uiService;
    private final IOService ioService;
    private Optional<Player> currentPlayer = Optional.empty();

    @Autowired
    public AuthenticationEngine(PlayerRepository playerRepo, UsernameRepository usernameRepo, UIService uiService, IOService ioService) {
        this.playerRepo = playerRepo;
        this.usernameRepo = usernameRepo;
        this.uiService = uiService;
        this.ioService = ioService;
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
        ioService.println("\n\tLogin");

        String givenUsername = this.uiService.promptForInput("""
                Enter username
                >\s""");

        String givenPassword = this.uiService.promptForInput("""
                Enter password
                >\s""");

        try {
            boolean authenticated = authenticate(givenUsername, givenPassword);
            if (authenticated) {
                Optional<Username> username = this.usernameRepo.findByValue(givenUsername);
                Optional<Player> player = playerRepo.findByUsername(username.get());
                setCurrentPlayer(player);
                this.ioService.println("\nLogin successful");
                return player;
            }
        } catch (AuthenticationException aE) {
            this.ioService.println(aE);
        }
        return Optional.empty();
    }

    @Override
    public void isLoggedIn() throws AuthenticationException {
        if (this.currentPlayer.isEmpty()) {
            throw new AuthenticationException("Please login first");
        }
    }

    public void logout() {
        this.currentPlayer = Optional.empty();
    }
}
