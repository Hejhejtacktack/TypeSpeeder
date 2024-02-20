package se.ju23.typespeeder.exception;

public class AuthenticationException extends NullPointerException {
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "AuthenticationException: " + getMessage();
    }
}
