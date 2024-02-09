package se.ju23.typespeeder.model;

import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    private Integer id;
    @Column(name = "account_name")
    private String accountName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
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
        return "Player{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", username=" + username +
                ", password='" + password + '\'' +
                '}';
    }
}
