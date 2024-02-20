package se.ju23.typespeeder.exception;

public class ChallengeException extends Exception {
    public ChallengeException() {
    }

    public ChallengeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ChallengeException: " + getMessage();
    }
}
