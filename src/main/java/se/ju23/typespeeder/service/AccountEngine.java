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

    @Autowired
    public AccountEngine(PlayerRepository playerRepo, UsernameRepository usernameRepo) {
        this.playerRepo = playerRepo;
        this.usernameRepo = usernameRepo;
    }

    @Override
    public void create(String givenAccountName, String givenUsername, String givenPassword) throws AccountCreationException {
        // Validate input parameters, e.g., check for empty or null values

        // Check if the username is already taken
        if (usernameRepo.existsByValue(givenUsername)) {
            throw new AccountCreationException("Error: Username is already taken.");
        }

        // Perform additional validation if needed

        // Create a new player with the provided information
        Player player = null;
        try {
            Username username = new Username(givenPassword);
            player = new Player(givenAccountName, username, givenPassword);
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
