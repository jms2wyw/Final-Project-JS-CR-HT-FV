package com.company.gamestore.service;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServiceLayerTest {

    GameServiceLayer gameServiceLayer;

    GameRepository gameRepository;

    private void setUpGameRepositoryMock() {
        gameRepository = mock(GameRepository.class);
        Game videoGame = new Game();
        videoGame.setGameId(1);
        videoGame.setTitle("Minecraft");
        videoGame.setDescription("Open world block building game");
        videoGame.setStudio("Mojang");
        videoGame.setEsrbRating("Everyone");
        videoGame.setPrice(new BigDecimal("34.99"));

        Game sameGame = new Game();
        sameGame.setTitle("Minecraft");
        sameGame.setDescription("Open world block building game");
        sameGame.setStudio("Mojang");
        sameGame.setEsrbRating("Everyone");
        sameGame.setPrice(new BigDecimal("34.99"));

        List<Game> gameList = new ArrayList<>();
        gameList.add(videoGame);

        doReturn(videoGame).when(gameRepository).save(sameGame);
        doReturn(Optional.of(videoGame)).when(gameRepository).findById(1);
        doReturn(gameList).when(gameRepository).findAll();
    }

    @BeforeEach
    public void setup() {
        setUpGameRepositoryMock();
    }

    @Test
    public void shouldSaveGame() {
        Game expectedResult = new Game();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("Open world block building game");
        expectedResult.setStudio("Mojang");
        expectedResult.setEsrbRating("Everyone");
        expectedResult.setPrice(new BigDecimal("34.99"));

        //Example of what someone would pass in
        Game game = new Game();
        game.setTitle("Minecraft");
        game.setDescription("Open world block building game");
        game.setStudio("Mojang");
        game.setEsrbRating("Everyone");
        game.setPrice(new BigDecimal("34.99"));

        // Test checks to see if object is stored/saved correctly
        game = gameRepository.save(game);

        assertEquals(expectedResult, game);
    }


}
