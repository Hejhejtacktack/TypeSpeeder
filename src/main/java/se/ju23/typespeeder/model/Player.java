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

    public Player() {
    }

    public Player(String accountName, Username username, String password) {
        this.accountName = accountName;
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "Account name: " + accountName +
                "Username: " + username +
                "Password: " + password;
    }
}
