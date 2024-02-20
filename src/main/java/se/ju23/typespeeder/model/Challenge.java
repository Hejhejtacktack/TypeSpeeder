package se.ju23.typespeeder.model;

import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.generator.CharacterGenerator;
import se.ju23.typespeeder.generator.Generator;
import se.ju23.typespeeder.generator.SentenceGenerator;
import se.ju23.typespeeder.generator.SymbolGenerator;

import java.time.Duration;
import java.time.LocalDateTime;

public class Challenge {

    Generator generator;
//    private SentenceGenerator sentenceGenerator;
//    private CharacterGenerator characterGenerator;
//    private SymbolGenerator symbolGenerator;
    private String correctInput;
    private double duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Challenge(String mode, String difficulty) throws ChallengeException {
        switch (mode) {
            case "1" -> this.generator = new SentenceGenerator(difficulty);
            case "2" -> this.generator = new CharacterGenerator(difficulty);
            case "3" -> this.generator = new SymbolGenerator(difficulty);
            default -> throw new ChallengeException("Mode must be Sentence, Character or Symbol");
        }
    }

    public Challenge() {
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
        this.endTime = LocalDateTime.now();
        this.duration = Duration.between(this.startTime, this.endTime).toMillis() / 1000.0;
    }

    public String lettersToType() {
        return this.generator.generate();
    }
}
