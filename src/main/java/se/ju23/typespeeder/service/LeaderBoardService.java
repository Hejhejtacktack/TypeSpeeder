package se.ju23.typespeeder.service;

import se.ju23.typespeeder.model.LeaderboardView;

import java.util.List;

public interface LeaderBoardService {
    List<LeaderboardView> getLeaderboard();
}
