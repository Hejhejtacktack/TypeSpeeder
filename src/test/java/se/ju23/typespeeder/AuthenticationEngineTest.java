package se.ju23.typespeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepository;
import se.ju23.typespeeder.repository.UsernameRepository;
import se.ju23.typespeeder.service.AuthenticationEngine;
import se.ju23.typespeeder.service.IOService;
import se.ju23.typespeeder.service.UIService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationEngineTest {

    AuthenticationEngine authenticationEngine;
    PlayerRepository playerRepo;
    UsernameRepository usernameRepo;
    UIService uiService;
    IOService ioService;

    @BeforeEach
    void setUp() {
        this.playerRepo = mock(PlayerRepository.class);
        this.usernameRepo = mock(UsernameRepository.class);
        this.uiService = mock(UIService.class);
        this.ioService = mock(IOService.class);
        this.authenticationEngine = new AuthenticationEngine(playerRepo, usernameRepo, uiService, ioService);
    }

    @Test
    public void testIsLoggedInWhenPlayerIsPresent() {
        Player mockedPlayer = mock(Player.class);
        this.authenticationEngine.setCurrentPlayer(Optional.of(mockedPlayer));

        assertDoesNotThrow(() -> authenticationEngine.isLoggedIn());
    }
}