package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.model.Challenge;

@Service
public class GameEngine implements GameService {

    UIEngine uiService;
    Challenge challenge;

    public GameEngine() {
    }

    public GameEngine(UIEngine uiService, Challenge challenge) {
        this.uiService = uiService;
        this.challenge = challenge;
    }

    @Override
    public void play() {
    }
}
