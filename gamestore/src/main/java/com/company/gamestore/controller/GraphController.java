package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;


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
}
