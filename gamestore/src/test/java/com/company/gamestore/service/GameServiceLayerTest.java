package com.company.gamestore.service;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.viewmodel.GameViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServiceLayerTest {
    GameRepository gameRepository;
    GameServiceLayer gameServiceLayer;

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
        gameServiceLayer = new GameServiceLayer(gameRepository);
    }

    @Test
    public void shouldFindGame() {
        GameViewModel expectedResult = new GameViewModel();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("Open world block building game");
        expectedResult.setStudio("Mojang");
        expectedResult.setEsrbRating("Everyone");
        expectedResult.setPrice(new BigDecimal("34.99"));

        GameViewModel expectedViewModel = gameServiceLayer.findGame(1);
        assertEquals(expectedViewModel, expectedResult);
    }

    @Test
    public void shouldFindAllGames() {
        GameViewModel expectedResult = new GameViewModel();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("Open world block building game");
        expectedResult.setStudio("Mojang");
        expectedResult.setEsrbRating("Everyone");
        expectedResult.setPrice(new BigDecimal("34.99"));

        List<GameViewModel> findGames = gameServiceLayer.findAllGames();
        assertEquals(expectedResult, findGames.get(0));
    }
}
