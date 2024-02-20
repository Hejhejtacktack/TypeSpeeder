package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.exception.PlayException;

public interface GameService {
    void play(String mode, String difficulty) throws PlayException;
}
