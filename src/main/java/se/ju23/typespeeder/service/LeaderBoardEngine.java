package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.repository.LeaderboardRepository;
import se.ju23.typespeeder.model.LeaderboardView;

import java.util.List;

@Service
public class LeaderBoardEngine implements LeaderBoardService {

    private final LeaderboardRepository leaderboardRepo;

    @Autowired
    public LeaderBoardEngine(LeaderboardRepository leaderboardRepo) {
        this.leaderboardRepo = leaderboardRepo;
    }

    @Override
    public List<LeaderboardView> getLeaderboard() {
        return leaderboardRepo.findAll();
    }
}
