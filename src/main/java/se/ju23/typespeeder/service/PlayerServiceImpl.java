package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.exception.AuthenticationException;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepository;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    PlayerRepository playerRepository;
    UserInterfaceService userInterfaceService;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, UserInterfaceService userInterfaceService) {
        this.playerRepository = playerRepository;
        this.userInterfaceService = userInterfaceService;
    }

    @Override
    public void changePlayerInfo(Optional<Player> player) throws AuthenticationException {
        Player currentPlayer;

        if (player.isPresent()) {
            currentPlayer = player.get();
        } else {
            throw new AuthenticationException("Error: Please login first.");
        }

        String choice = userInterfaceService.promptForInput("""
                
                What do you want to do?
                1. Change Account name
                2. Change Username
                3. Change password
                0. Back
                Your choice
                >\s""");

        switch (choice) {
            case "1" -> changeAccountName(currentPlayer, "hej");
            case "2" -> changeUsername(currentPlayer, "hej");
            case "3" -> changePassword(currentPlayer, "hej");
            case "0" -> {}
        }
    }

    @Override
    public boolean changeAccountName(Player player, String newAccountName) {
        return false;
    }

    @Override
    public boolean changeUsername(Player player, String newUsername) {
        return false;
    }

    @Override
    public boolean changePassword(Player player, String newPassword) {
        return false;
    }
}
