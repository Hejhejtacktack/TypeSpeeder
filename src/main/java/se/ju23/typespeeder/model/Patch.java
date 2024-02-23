package se.ju23.typespeeder.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patch {

    public String patchVersion;
    public LocalDateTime realeaseDateTime;

    public Patch(String patchVersion, LocalDateTime releaseDateTime) {
        this.patchVersion = patchVersion;
        this.realeaseDateTime = releaseDateTime;
    }

    public Patch() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.realeaseDateTime = LocalDateTime.now();
        this.patchVersion = "1";
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public LocalDateTime getRealeaseDateTime() {
        return realeaseDateTime;
    }
}
