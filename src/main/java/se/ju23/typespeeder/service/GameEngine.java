package se.ju23.typespeeder.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GameEngine implements GameService {

    private final IOService ioService;

    public GameEngine(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void play() {
    }
}
