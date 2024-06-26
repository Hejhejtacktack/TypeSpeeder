package se.ju23.typespeeder.service;

import se.ju23.typespeeder.exception.AuthenticationException;
import se.ju23.typespeeder.model.Player;

import java.util.Optional;

public interface PlayerService {
    void changePlayerInfo(Optional<Player> player) throws AuthenticationException;
    boolean changeAccountName(Player player, String newAccountName);
    boolean changeUsername(Player player, String newUsername);
    boolean changePassword(Player player, String newPassword);
    boolean updateScore(Player player, double score);
    boolean levelUp(Player player);
}
