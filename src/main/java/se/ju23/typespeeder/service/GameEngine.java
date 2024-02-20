package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.exception.PlayException;
import se.ju23.typespeeder.generator.CharacterGenerator;
import se.ju23.typespeeder.generator.SentenceGenerator;
import se.ju23.typespeeder.generator.SymbolGenerator;
import se.ju23.typespeeder.model.Challenge;

@Service
public class GameEngine implements GameService {

    UIEngine uiService;

    public GameEngine() {
    }

    @Autowired
    public GameEngine(UIEngine uiService) {
        this.uiService = uiService;
    }

    @Override
    public void play(String mode, String difficulty) throws PlayException {
        Challenge challenge;
        try {
            challenge = new Challenge(mode, difficulty);
        } catch (ChallengeException cE) {
            throw new PlayException("caused by: " + cE);
        }
        this.uiService.promptForInput("""
                
                You are to type the given characters/symbols/sentence
                Every correct character gives points
                The faster the better
                Press enter to start...""");
        String answer = this.uiService.promptForInput("\n" + challenge.startChallenge() + "\n> ");
    }
}
