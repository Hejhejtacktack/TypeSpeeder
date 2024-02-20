package se.ju23.typespeeder.model;

import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.generator.CharacterGenerator;
import se.ju23.typespeeder.generator.SentenceGenerator;
import se.ju23.typespeeder.generator.SymbolGenerator;

import java.time.Duration;
import java.time.LocalDateTime;

public class Challenge {

    private SentenceGenerator sentenceGenerator;
    private CharacterGenerator characterGenerator;
    private SymbolGenerator symbolGenerator;
    private String correctInput;
    private double timeSpent;

    public Challenge(String mode, String difficulty) throws ChallengeException {
        switch (mode) {
            case "1" -> this.sentenceGenerator = new SentenceGenerator(difficulty);
            case "2" -> this.characterGenerator = new CharacterGenerator(difficulty);
            case "3" -> this.symbolGenerator = new SymbolGenerator(difficulty);
            default -> throw new ChallengeException("Error in Challenge: Mode must be Sentence, Character or Symbol");
        }
    }

    public Challenge() {
    }

    public String getCorrectInput() {
        return correctInput;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public String startChallenge() {
        LocalDateTime startTime = LocalDateTime.now();
        this.correctInput = this.lettersToType();
        LocalDateTime endTime = LocalDateTime.now();

        this.timeSpent = Duration.between(startTime, endTime).toMillis() * 1000.0;

        return this.correctInput;
    }

    public String lettersToType() {
        return this.sentenceGenerator.generate();
    }
}
