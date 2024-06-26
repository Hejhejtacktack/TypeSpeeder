package se.ju23.typespeeder.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.generator.CharacterGenerator;
import se.ju23.typespeeder.generator.Generator;
import se.ju23.typespeeder.generator.SentenceGenerator;
import se.ju23.typespeeder.generator.SymbolGenerator;
import se.ju23.typespeeder.service.MessageBundle;
import se.ju23.typespeeder.service.WordProvider;

import java.time.Duration;
import java.time.LocalDateTime;

public class Challenge {

    private Generator<?> generator;
    private String correctInput;
    private double duration;
    private LocalDateTime startTime;

    public Challenge(String mode, String difficulty) throws ChallengeException {
        switch (mode) {
            case "1" -> this.generator = new SentenceGenerator(difficulty);
            case "2" -> this.generator = new CharacterGenerator(difficulty);
            case "3" -> this.generator = new SymbolGenerator(difficulty);
            default -> throw new ChallengeException("Mode must be Sentence, Character or Symbol");
        }
    }

    public Challenge() {
        try {
            this.generator = new SentenceGenerator("1");
        } catch (ChallengeException e) {
            throw new RuntimeException(e);
        }
    }

    public Generator<?> getGenerator() {
        return this.generator;
    }

    public String getCorrectInput() {
        return correctInput;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String startChallenge() {
        this.startTime = LocalDateTime.now();
        this.correctInput = this.lettersToType();

        return this.correctInput;
    }

    public void endChallenge() {
        LocalDateTime endTime = LocalDateTime.now();
        this.duration = Duration.between(this.startTime, endTime).toMillis() / 1000.0;
    }

    public String lettersToType() {
        return this.generator.generate();
    }
}
