package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepository;

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
    public void changePlayerInfo(Player player) {
        String choice = userInterfaceService.promptForInput("""
                
                What do you want to do?
                1. Change Account name
                2. Change Username
                3. Change password
                0. Back
                Your choice
                >\s""");

        switch (choice) {
            case "1" -> changeAccountName(player, "hej");
            case "2" -> changeUsername(player, "hej");
            case "3" -> changePassword(player, "hej");
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
