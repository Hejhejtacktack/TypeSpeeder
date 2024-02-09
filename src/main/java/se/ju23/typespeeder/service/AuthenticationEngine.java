package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.AuthenticationException;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepository;

import java.util.Optional;

@Service
public class AuthenticationEngine implements AuthenticationService {

    @Override
    public boolean authenticate(String username, String password) throws AuthenticationException {
        // TODO implement
        return true;
    }
}
