package se.ju23.typespeeder.exception;

public class AccountCreationException extends Exception {
    public AccountCreationException() {
    }

    public AccountCreationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "AccountCreationException: " + getMessage();
    }
}
