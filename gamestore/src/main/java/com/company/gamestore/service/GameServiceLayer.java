package com.company.gamestore.service;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GameServiceLayer {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    public GameServiceLayer(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Helper function
    public GameViewModel buildGameViewModel(Game game) {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGameId(game.getGameId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;
    }

    @org.springframework.transaction.annotation.Transactional
    public GameViewModel saveGame(GameViewModel viewModel) {
        if (viewModel.equals(null)) {
            throw new NotFoundException("Game not found!");
        }

        //Persist the game
        Game newGame = new Game();
        newGame.setTitle(viewModel.getTitle());
        newGame.setDescription(viewModel.getDescription());
        newGame.setStudio(viewModel.getStudio());
        newGame.setEsrbRating(viewModel.getEsrbRating());
        newGame.setPrice(viewModel.getPrice());
        newGame.setQuantity(viewModel.getQuantity());
        newGame = gameRepository.save(newGame);

        viewModel.setGameId(newGame.getGameId());

        return viewModel;
    }

    @org.springframework.transaction.annotation.Transactional
    public GameViewModel updateGame(GameViewModel gameViewModel) {
        if (gameViewModel.equals(null)) {
            throw new NotFoundException("Game not found!");
        }

        Game game = new Game();
        game.setGameId(gameViewModel.getGameId());
        game.setTitle(gameViewModel.getTitle());
        game.setDescription(gameViewModel.getDescription());
        game.setStudio(gameViewModel.getStudio());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setPrice(gameViewModel.getPrice());
        game.setQuantity(gameViewModel.getQuantity());

        //Update the album
        try {
            game = gameRepository.save(game);

            return buildGameViewModel(game);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @org.springframework.transaction.annotation.Transactional
    public void removeGame(int id) {
        gameRepository.deleteById(id);
    }

    public GameViewModel findGame(int id) {
        Optional<Game> target = gameRepository.findById(id);

        if (target.isEmpty()) {
            throw new NotFoundException("Game not found!");
        }

        return buildGameViewModel(target.get());
    }

    public List<GameViewModel> findAllGames() {
        List<Game> gameList = gameRepository.findAll();
        List<GameViewModel> viewModelList = new ArrayList<>();

        for (Game game : gameList) {
            GameViewModel gameViewModel = buildGameViewModel(game);
            viewModelList.add(gameViewModel);
        }

        return viewModelList;
    }

    public List<GameViewModel> findGamesByStudio(String studio) {
        List<Game> gameList = gameRepository.findByStudio(studio);
        List<GameViewModel> viewModelList = new ArrayList<>();

        for (Game game : gameList) {
            GameViewModel viewModel = buildGameViewModel(game);
            viewModelList.add(viewModel);
        }

        return viewModelList;
    }

    public List<GameViewModel> findGamesByEsrbRating(String rating) {
        List<Game> gameList = gameRepository.findByesrbRating(rating);
        List<GameViewModel> viewModelList = new ArrayList<>();

        for(Game game : gameList) {
            GameViewModel viewModel = buildGameViewModel(game);
            viewModelList.add(viewModel);
        }

        return viewModelList;
    }

    public GameViewModel findGameByTitle(String title) {
        Optional<Game> target = gameRepository.findByTitle(title);

        if (target.isEmpty()) {
            throw new NotFoundException("Game not found!");
        }

        return buildGameViewModel(target.get());
    }
}
