package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.exception.AuthenticationException;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepository;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    PlayerRepository playerRepository;
    UIService uiService;
    MessageBundle messageBundle;

    public PlayerServiceImpl(PlayerRepository playerRepository, UIService uiService, MessageBundle messageBundle) {
        this.playerRepository = playerRepository;
        this.uiService = uiService;
        this.messageBundle = messageBundle;
    }

    @Override
    public void changePlayerInfo(Optional<Player> player) throws AuthenticationException {
        Player currentPlayer;

        if (player.isPresent()) {
            currentPlayer = player.get();
        } else {
            throw new AuthenticationException("Please login first.");
        }

        String choice = this.uiService.promptForInput(this.messageBundle.getMessage("player.changeMenu") + "\n> ");

        switch (choice) {
            case "1" -> changeAccountName(currentPlayer, this.uiService.promptForInput(this.messageBundle.getMessage("player.newAccountName") + "\n> "));
            case "2" -> changeUsername(currentPlayer, this.uiService.promptForInput(this.messageBundle.getMessage("player.newUsername") + "\n> "));
            case "3" -> changePassword(currentPlayer, this.uiService.promptForInput(this.messageBundle.getMessage("player.newPassword") + "\n> "));
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
        if (score > 0) {
            player.setScore(player.getScore() + score);
            this.playerRepository.save(player);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean levelUp(Player player) {
        int playerLevel = player.getLevel();
        int threshold = playerLevel * 75;
        double playerScore = player.getScore();

        if (playerScore >= threshold) {
            player.setLevel(playerLevel + 1);
            this.playerRepository.save(player);
            return true;
        } else {
            return false;
        }
    }
}
