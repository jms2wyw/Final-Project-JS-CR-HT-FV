package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameRepositoryTests {

    @Autowired
    GameRepository gameRepository;

    @BeforeEach
    public void setup() {
        gameRepository.deleteAll();
    }


    @Test
    public void shouldAddGameAndFindById() {
        Game videoGame = new Game();
        videoGame.setTitle("Slay the Spire");
        videoGame.setDescription("Card game about climbing the spire");
        videoGame.setStudio("Mega Crit Games");
        videoGame.setEsrbRating("Everyone");
        videoGame.setPrice(new BigDecimal("24.99"));

        videoGame = gameRepository.save(videoGame);

        //Assert
        Optional<Game> target = gameRepository.findById(videoGame.getGameId());

        assertEquals(target.get(), videoGame);
    }


    @Test
    public void shouldGetAllGames() {
        Game videoGame = new Game();
        videoGame.setTitle("Slay the Spire");
        videoGame.setDescription("Card game about climbing the spire");
        videoGame.setStudio("Mega Crit Games");
        videoGame.setEsrbRating("Everyone");
        videoGame.setPrice(new BigDecimal("24.99"));

        videoGame = gameRepository.save(videoGame);

        Game otherGame = new Game();
        otherGame.setTitle("Minecraft");
        otherGame.setDescription("Open world block building game");
        otherGame.setStudio("Mojang");
        otherGame.setEsrbRating("Everyone");
        otherGame.setPrice(new BigDecimal("34.99"));

        otherGame = gameRepository.save(otherGame);

        //Assert
        List<Game> allGames = gameRepository.findAll();

        assertEquals(allGames.size(), 2);
    }


    @Test
    public void shouldUpdateGame() {
        Game videoGame = new Game();
        videoGame.setTitle("Slay the Spire");
        videoGame.setDescription("Card game about climbing the spire");
        videoGame.setStudio("Mega Crit Games");
        videoGame.setEsrbRating("Everyone");
        videoGame.setPrice(new BigDecimal("24.99"));

        videoGame = gameRepository.save(videoGame);

        //Change price and update
        videoGame.setEsrbRating("Teen");
        videoGame.setPrice(new BigDecimal("19.99"));

        gameRepository.save(videoGame);

        //Assert
        Optional<Game> compare = gameRepository.findById(videoGame.getGameId());
        assertEquals(compare.get(), videoGame);
    }


    @Test
    public void shouldDeleteGame() {
        Game videoGame = new Game();
        videoGame.setTitle("Slay the Spire");
        videoGame.setDescription("Card game about climbing the spire");
        videoGame.setStudio("Mega Crit Games");
        videoGame.setEsrbRating("Everyone");
        videoGame.setPrice(new BigDecimal("24.99"));

        videoGame = gameRepository.save(videoGame);

        //Assert
        Optional<Game> target = gameRepository.findById(videoGame.getGameId());
        assertEquals(target.get(), videoGame);

        gameRepository.deleteById(videoGame.getGameId());
        target = gameRepository.findById(videoGame.getGameId());

        assertFalse(target.isPresent());
    }


    @Test
    public void shouldFindGamesByStudio() {
        Game videoGame = new Game();
        videoGame.setTitle("Slay the Spire");
        videoGame.setDescription("Card game about climbing the spire");
        videoGame.setStudio("Mega Crit Games");
        videoGame.setEsrbRating("Everyone");
        videoGame.setPrice(new BigDecimal("24.99"));

        videoGame = gameRepository.save(videoGame);

        Game otherGame = new Game();
        otherGame.setTitle("Minecraft");
        otherGame.setDescription("Open world block building game");
        otherGame.setStudio("Mojang");
        otherGame.setEsrbRating("Everyone");
        otherGame.setPrice(new BigDecimal("34.99"));

        otherGame = gameRepository.save(otherGame);

        //Add second game from other studio
        Game secondStudioGame = new Game();
        secondStudioGame.setTitle("Minecraft Legends");
        secondStudioGame.setDescription("Weirder game based in Minecraft world");
        secondStudioGame.setStudio("Mojang");
        secondStudioGame.setEsrbRating("Everyone");
        secondStudioGame.setPrice(new BigDecimal("24.99"));

        secondStudioGame = gameRepository.save(secondStudioGame);

        //Assert
        List<Game> gamesByMega = gameRepository.findByStudio("Mega Crit Games");
        List<Game> gamesByMojang = gameRepository.findByStudio("Mojang");

        assertEquals(gamesByMega.size(), 1);
        assertEquals(gamesByMojang.size(), 2);
    }


    @Test
    public void shouldFindGamesByRating() {
        Game videoGame = new Game();
        videoGame.setTitle("Slay the Spire");
        videoGame.setDescription("Card game about climbing the spire");
        videoGame.setStudio("Mega Crit Games");
        videoGame.setEsrbRating("Teen");
        videoGame.setPrice(new BigDecimal("24.99"));

        videoGame = gameRepository.save(videoGame);

        Game otherGame = new Game();
        otherGame.setTitle("Minecraft");
        otherGame.setDescription("Open world block building game");
        otherGame.setStudio("Mojang");
        otherGame.setEsrbRating("Everyone");
        otherGame.setPrice(new BigDecimal("34.99"));

        otherGame = gameRepository.save(otherGame);

        //Add second game from other studio
        Game secondStudioGame = new Game();
        secondStudioGame.setTitle("Minecraft Legends");
        secondStudioGame.setDescription("Weirder game based in Minecraft world");
        secondStudioGame.setStudio("Mojang");
        secondStudioGame.setEsrbRating("Everyone");
        secondStudioGame.setPrice(new BigDecimal("24.99"));

        secondStudioGame = gameRepository.save(secondStudioGame);

        //Assert
        List<Game> gamesRatedE = gameRepository.findByesrbRating("Everyone");
        List<Game> gamesRatedT = gameRepository.findByesrbRating("Teen");

        assertEquals(gamesRatedE.size(), 2);
        assertEquals(gamesRatedT.size(), 1);
    }


    @Test
    public void shouldFindGameByTitle() {
        Game videoGame = new Game();
        videoGame.setTitle("Slay the Spire");
        videoGame.setDescription("Card game about climbing the spire");
        videoGame.setStudio("Mega Crit Games");
        videoGame.setEsrbRating("Teen");
        videoGame.setPrice(new BigDecimal("24.99"));

        videoGame = gameRepository.save(videoGame);

        Game otherGame = new Game();
        otherGame.setTitle("Minecraft");
        otherGame.setDescription("Open world block building game");
        otherGame.setStudio("Mojang");
        otherGame.setEsrbRating("Everyone");
        otherGame.setPrice(new BigDecimal("34.99"));

        otherGame = gameRepository.save(otherGame);

        //Add second game from other studio
        Game secondStudioGame = new Game();
        secondStudioGame.setTitle("Minecraft Legends");
        secondStudioGame.setDescription("Weirder game based in Minecraft world");
        secondStudioGame.setStudio("Mojang");
        secondStudioGame.setEsrbRating("Everyone");
        secondStudioGame.setPrice(new BigDecimal("24.99"));

        //Assert
        String title = "Minecraft";
        Optional<Game> target = gameRepository.findByTitle(title);

        assertEquals(target.get(), otherGame);
    }
}
