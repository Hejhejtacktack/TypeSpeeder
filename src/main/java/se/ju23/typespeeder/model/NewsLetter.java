package se.ju23.typespeeder.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "news_letters")
public class NewsLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "publish_date_time")
    public LocalDateTime publishDateTime;
    @Column(name = "content")
    public String content;

    public NewsLetter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.publishDateTime = LocalDateTime.now();
        this.content = "This application is under construction.\n" +
                "Soon the game will be implemented. For now you can create an account and view the leaderboard.";
    }

    public NewsLetter(LocalDateTime publishDateTime, String content) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.publishDateTime = publishDateTime;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }

    @Override
    public String toString() {
        return "\t\t" + publishDateTime + "\n" + content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
