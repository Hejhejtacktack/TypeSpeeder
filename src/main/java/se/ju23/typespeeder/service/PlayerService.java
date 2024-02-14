package se.ju23.typespeeder.service;

import se.ju23.typespeeder.model.Player;

public interface PlayerService {
    void changePlayerInfo(Player player);
    boolean changeAccountName(Player player, String newAccountName);
    boolean changeUsername(Player player, String newUsername);
    boolean changePassword(Player player, String newPassword);
}
