package se.ju23.typespeeder.service;

import se.ju23.typespeeder.exception.AuthenticationException;
import se.ju23.typespeeder.exception.ValidationException;
import se.ju23.typespeeder.model.Player;

import java.util.Optional;

public interface AuthenticationService {
    Optional<Player> getCurrentPlayer();
    boolean authenticate(String givenUsername, String givenPassword) throws AuthenticationException;
    void validate(String string) throws ValidationException;
    Optional<Player> login();
    void logout();
    void isLoggedIn() throws AuthenticationException;
}
