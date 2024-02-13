package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.AuthenticationException;
import se.ju23.typespeeder.model.Player;

import java.util.Optional;

public interface AuthenticationService {
    boolean authenticate(String givenUsername, String givenPassword) throws AuthenticationException;
    Optional<Player> login();
}
