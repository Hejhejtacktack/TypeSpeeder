package se.ju23.typespeeder.exception;

public class ValidationException extends Exception {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ValidationException: " + getMessage();
    }
}
