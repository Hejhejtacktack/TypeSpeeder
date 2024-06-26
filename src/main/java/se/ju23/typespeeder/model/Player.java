package se.ju23.typespeeder.model;

import jakarta.persistence.*;
import se.ju23.typespeeder.exception.ValidationException;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_name")
    private String accountName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username_id", referencedColumnName = "id")
    private Username username;
    @Column(name = "password")
    private String password;
    @Column(name = "score")
    private double score;
    @Column(name = "level")
    private int level;

    public Player() {
    }

    public Player(String accountName, Username username, String password) throws ValidationException {
        validate(accountName);
        validate(username.getValue());
        validate(password);
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.score = 0;
        this.level = 1;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private void validate(String input) throws ValidationException {
        if (input == null || input.isEmpty()) {
            throw new ValidationException("Input cannot be empty");
        } else if (!input.matches("[a-zA-z-1-9]+")) {
            throw new ValidationException("Input must be characters or digits");
        }
    }

    // TODO update
    @Override
    public String toString() {
        return "Player{" +
                "accountName='" + accountName + '\'' +
                ", username=" + username +
                ", password='" + password + '\'' +
                ", score=" + score +
                '}';
    }
}
