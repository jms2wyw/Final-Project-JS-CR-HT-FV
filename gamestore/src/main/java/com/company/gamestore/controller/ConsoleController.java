package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.service.ConsoleServiceLayer;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleServiceLayer consoleServiceLayer;

    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel addConsole(@RequestBody ConsoleViewModel consoleViewModel) {
        return consoleServiceLayer.saveConsole(consoleViewModel);
    }

    @GetMapping("/console/{id}")
    public ConsoleViewModel getConsoleById(@PathVariable int id){
        return consoleServiceLayer.findConsoleById(id);

    }

    @GetMapping("/consoles")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsoles() {
        return consoleServiceLayer.findAllConsoles();
    }

    @PutMapping("/console")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ConsoleViewModel updateConsole(@RequestBody ConsoleViewModel consoleViewModel) {
        return consoleServiceLayer.updateConsole(consoleViewModel);
    }

    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id){
        consoleServiceLayer.deleteConsole(id);
    }


    @GetMapping("/console/manufacturer/{manufacturer}") //get by color
    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return consoleServiceLayer.findConsolesByManufacturer(manufacturer);
    }
}

