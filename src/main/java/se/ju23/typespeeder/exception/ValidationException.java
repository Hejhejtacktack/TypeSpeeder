package se.ju23.typespeeder.exception;

public class ValidationException extends Exception {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super("\n" + message + "\n");
    }
}
