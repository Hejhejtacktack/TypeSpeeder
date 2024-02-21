package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.exception.PlayException;
import se.ju23.typespeeder.model.Challenge;

@Service
public class GameEngine implements GameService {

    UIEngine uiService;
    Challenge challenge;

    @Autowired
    public GameEngine(UIEngine uiService) {
        this.uiService = uiService;
    }

    public GameEngine() {
    }

    @Override
    public double play(String mode, String difficulty) throws PlayException {
        try {
            this.challenge = new Challenge(mode, difficulty);
        } catch (ChallengeException cE) {
            throw new PlayException("caused by: " + cE);
        }

        this.uiService.promptForInput("""
                
                You are to type the given characters on the screen
                Every correct character gives points
                The faster the better
                Press enter to start...
                """);

        String userInput = this.uiService.promptForInput(challenge.startChallenge() + "\n> ").trim();
        this.challenge.endChallenge();

        String correctInput = this.challenge.getCorrectInput();

        this.uiService.promptForInput(
                "Correct characters: " + this.getNumOfCorrectChars(userInput, correctInput) + "\n" +
                "Time spent: " + this.challenge.getDuration() + "\n" +
                "Press enter to continue...");

        return calculateScore(userInput, correctInput, this.challenge.getGenerator().getDifficulty());
    }

    private double calculateScore(String userInput, String correctInput, String difficulty) {
        double score = (getNumOfCorrectChars(userInput, correctInput) * 10) / getChallengeDuration();
        score = score * Integer.parseInt(difficulty);
        return Math.floor(score * 100) / 100;
    }

    private int getNumOfCorrectChars(String userInput, String correctInput) {
        int minLen = Math.min(userInput.length(), correctInput.length());
        int correctChars = 0;

        for (int i = 0; i < minLen; i++) {
            if (userInput.charAt(i) == correctInput.charAt(i)) {
                correctChars++;
            }
        }

        return correctChars;
    }

    private double getChallengeDuration() {
        return this.challenge.getDuration();
    }
}
