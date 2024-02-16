package se.ju23.typespeeder.exception;

public class AuthenticationException extends NullPointerException {
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super("\n" + message + "\n");
    }
}
