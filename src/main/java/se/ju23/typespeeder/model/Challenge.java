package se.ju23.typespeeder.model;

import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.generator.CharacterGenerator;
import se.ju23.typespeeder.generator.SentenceGenerator;
import se.ju23.typespeeder.generator.SymbolGenerator;

public class Challenge {

    SentenceGenerator sentenceGenerator;
    CharacterGenerator characterGenerator;
    SymbolGenerator symbolGenerator;

    public Challenge(String mode, String difficulty) throws ChallengeException {
        switch (mode) {
            case "1" -> this.sentenceGenerator = new SentenceGenerator(difficulty);
            case "2" -> this.characterGenerator = new CharacterGenerator(difficulty);
            case "3" -> this.symbolGenerator = new SymbolGenerator(difficulty);
            default -> throw new ChallengeException("Error in Challenge: Mode must be BLABLA, BLABLA or BLABLA");
        }
    }

    public Challenge() {
    }

    public String startChallenge() {
        return this.lettersToType();
    }

    public String lettersToType() {
        return this.sentenceGenerator.generate();
    }
}
