package se.ju23.typespeeder;

public class AuthenticationException extends NullPointerException {
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super("\n" + message + "\n");
    }
}
