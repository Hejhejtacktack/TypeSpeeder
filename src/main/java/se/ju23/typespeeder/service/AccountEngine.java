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

    @Autowired
    public AccountEngine(PlayerRepository playerRepo, UsernameRepository usernameRepo, UIService uiService) {
        this.playerRepo = playerRepo;
        this.usernameRepo = usernameRepo;
        this.uiService = uiService;
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
        // Validate input parameters, e.g., check for empty or null values

        // Check if the username is already taken
        if (usernameRepo.existsByValue(desiredUsername)) {
            throw new AccountCreationException("Error: Username is already taken.");
        }

        // Perform additional validation if needed

        // Create a new player with the provided information
        Player player;
        try {
            Username username = new Username(desiredPassword);
            player = new Player(desiredAccountName, username, desiredPassword);
        } catch (ValidationException vE) {
            throw new AccountCreationException("Error: in Account creation: " + vE);
        }

        // Save the player to the database
        playerRepo.save(player);
    }

    @Override
    public void remove(String username) {

    }
}
