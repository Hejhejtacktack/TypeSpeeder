package se.ju23.typespeeder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;

@Entity(name = "leaderboard")
@Immutable
public class LeaderboardView {

    @Id
    @Column(name = "account_name")
    private String accountName;

    @Column(name = "score")
    private double score;

    public String getAccountName() {
        return accountName;
    }

    public double getScore() {
        return score;
    }
}
