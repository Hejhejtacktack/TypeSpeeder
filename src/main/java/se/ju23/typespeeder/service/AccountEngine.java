package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.exception.AccountCreationException;
import se.ju23.typespeeder.exception.ValidationException;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.model.Username;
import se.ju23.typespeeder.repository.PlayerRepository;
import se.ju23.typespeeder.repository.UsernameRepository;

@Service
public class AccountEngine implements AccountService {

    PlayerRepository playerRepo;
    UsernameRepository usernameRepo;
    UIService uiService;
    AuthenticationService authenticationService;

    @Autowired
    public AccountEngine(PlayerRepository playerRepo, UsernameRepository usernameRepo, UIService uiService, AuthenticationService authenticationService) {
        this.playerRepo = playerRepo;
        this.usernameRepo = usernameRepo;
        this.uiService = uiService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void build() throws AccountCreationException {
        String desiredAccountName = this.uiService.promptForInput("""
                Enter desired account name
                >\s""");
        String desiredUsername = this.uiService.promptForInput("""
                Enter desired username
                >\s""");
        String desiredPassword = this.uiService.promptForInput("""
                Enter desired password
                >\s""");

        this.create(desiredAccountName, desiredUsername, desiredPassword);
    }

    @Override
    public void create(String desiredAccountName, String desiredUsername, String desiredPassword) throws AccountCreationException {
        try {
            this.authenticationService.validate(desiredUsername);
        } catch (ValidationException e) {
            throw new AccountCreationException("Caused by: " + e);
        }

        Player player;
        try {
            Username username = new Username(desiredPassword);
            player = new Player(desiredAccountName, username, desiredPassword);
        } catch (ValidationException vE) {
            throw new AccountCreationException("Caused by: " + vE);
        }

        playerRepo.save(player);
    }

    @Override
    public void remove(String username) {
        this.usernameRepo.deleteAll();
    }
}
