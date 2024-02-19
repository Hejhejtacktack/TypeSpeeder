package se.ju23.typespeeder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsLetter {

    public LocalDateTime publishDateTime;
    public String content;

    public NewsLetter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.publishDateTime = LocalDateTime.now();
        this.content = "Default content";
    }

    public NewsLetter(LocalDateTime publishDateTime, String content) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
}
