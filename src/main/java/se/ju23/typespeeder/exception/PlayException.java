package se.ju23.typespeeder.exception;

public class PlayException extends Exception {

    public PlayException() {
    }

    public PlayException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "PlayException: " + getMessage();
    }
}
