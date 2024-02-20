package se.ju23.typespeeder.repository;

import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.model.LeaderboardView;

import java.util.List;

@Repository
public interface LeaderboardRepository extends ReadOnlyRepository<LeaderboardView, String> {
    List<LeaderboardView> findAll();
}
