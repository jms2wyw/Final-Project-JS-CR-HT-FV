package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.ConsoleRepository;


import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.viewmodel.GameViewModel;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    ConsoleRepository consoleRepository;

    @Autowired
    GameRepository gameRepository;

    @QueryMapping
    public Console findConsoleById(@Argument int id){
        Optional<Console> target = consoleRepository.findById(id);
        return target.isPresent() ? target.get() : null;

    }
    @QueryMapping
    public List<Console> findConsoles(){

        return consoleRepository.findAll();

    }
    @QueryMapping
    public List<Console> findConsoleByManufacturer(@Argument String manufacturer){

        return consoleRepository.findByManufacturer(manufacturer);

    }

    @QueryMapping
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    @QueryMapping
    public Game findGameById(@Argument int id) {
        Optional<Game> target = gameRepository.findById(id);

        return target.isPresent() ? target.get() : null;
    }

    @QueryMapping
    public Game findGameByTitle(@Argument String title) {
        Optional<Game> target = gameRepository.findByTitle(title);

        return target.isPresent() ? target.get() : null;
    }

    @QueryMapping
    public List<Game> findGamesByEsrbRating(@Argument String esrbRating) {
        return gameRepository.findByesrbRating(esrbRating);
    }

    @QueryMapping
    public List<Game> findGamesByStudio(@Argument String studio) {
        return gameRepository.findByStudio(studio);
    }
}
