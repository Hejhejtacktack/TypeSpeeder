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
    UIService UIEngine;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, UIService UIEngine) {
        this.playerRepository = playerRepository;
        this.UIEngine = UIEngine;
    }

    @Override
    public void changePlayerInfo(Optional<Player> player) throws AuthenticationException {
        Player currentPlayer;

        if (player.isPresent()) {
            currentPlayer = player.get();
        } else {
            throw new AuthenticationException("Please login first.");
        }

        String choice = UIEngine.promptForInput("""
                
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

    @Override
    public boolean updateScore(Player player, double score) {
        player.setScore(score);
        this.playerRepository.save(player);

        return true;
    }

    @Override
    public boolean levelUp(Player player) {
        int playerLevel = player.getLevel();
        int threshold = playerLevel * 75;
        double playerScore = player.getScore();

        if (playerScore >= threshold) {
            player.setLevel(+1);
            this.playerRepository.save(player);
            return true;
        } else {
            return false;
        }
    }
}
