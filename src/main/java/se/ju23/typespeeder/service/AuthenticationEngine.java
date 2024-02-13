package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.AuthenticationException;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.model.Username;
import se.ju23.typespeeder.repository.PlayerRepository;
import se.ju23.typespeeder.repository.UsernameRepository;

import java.util.Optional;

@Service
public class AuthenticationEngine implements AuthenticationService {

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private UsernameRepository usernameRepo;
    private final IOService ioService;

    public AuthenticationEngine(IOService ioService) {
        this.ioService = ioService;
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
    public Optional<Player> login() {
        ioService.println("\n\tLogin");

        ioService.println("Enter username");
        ioService.print(">");
        String givenUsername = ioService.readString();

        ioService.println("Enter password");
        ioService.print(">");
        String givenPassword = ioService.readString();

        try {
            boolean authenticated = authenticate(givenUsername, givenPassword);
            if (authenticated) {
                Optional<Username> username = this.usernameRepo.findByValue(givenUsername);
                return playerRepo.findByUsername(username.get());
            }
        } catch (AuthenticationException e) {

        }
        return Optional.empty();
    }
}
