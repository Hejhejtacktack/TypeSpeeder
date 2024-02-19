package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameEngine implements GameService {

    UIEngine uiService;

    public GameEngine() {
    }

    @Autowired
    public GameEngine(UIEngine uiService) {
        this.uiService = uiService;
    }

    @Override
    public void play() {
    }
}
