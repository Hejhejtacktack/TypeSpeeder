package se.ju23.typespeeder.model;

import jakarta.persistence.*;

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

    public Player() {
    }

    public Player(String accountName, Username username, String password, double score) {
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public String getAccountName() {
        return accountName;
    }

    public Username getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
