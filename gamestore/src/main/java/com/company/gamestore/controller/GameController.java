package com.company.gamestore.controller;

import com.company.gamestore.service.GameServiceLayer;
import com.company.gamestore.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    GameServiceLayer gameServiceLayer;

    @GetMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames() {

        return gameServiceLayer.findAllGames();
    }

    @GetMapping("/games/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGameById(@PathVariable @NotNull int id) {

        return gameServiceLayer.findGame(id);
    }

    @GetMapping("/games/studios/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable @NotNull String studio) {
        return gameServiceLayer.findGamesByStudio(studio);
    }

    @GetMapping("/games/ratings/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByEsrbRating(@PathVariable @NotNull String rating) {
        return gameServiceLayer.findGamesByEsrbRating(rating);
    }

    @GetMapping("/games/titles/{title}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGameByTitle(@PathVariable @NotNull String title) {
        return gameServiceLayer.findGameByTitle(title);
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel addGame(@RequestBody @Valid GameViewModel gameViewModel) {
        return gameServiceLayer.saveGame(gameViewModel);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel updateGame(@RequestBody @Valid GameViewModel gameViewModel) {
        return gameServiceLayer.updateGame(gameViewModel);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        gameServiceLayer.removeGame(id);
    }
}
