package com.company.gamestore.controller;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@RestController
public class TshirtController {
    //create, read, read all, update, delete, color, and size T-shirt information
    @Autowired
    TshirtRepository repo;
    @GetMapping("/tshirt") //get all
    public List<Tshirt> getTshirts() {
        return repo.findAll();
    }
    @GetMapping("/tshirt/id/{id}") //get by id
    public Tshirt getTshirtrById(@PathVariable Integer tshirtId) {
        return repo.findById(tshirtId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));
    }
    @GetMapping("/tshirt/color/{color}") //get by color
    public List<Tshirt> getTshirtByColor(@PathVariable String color) {
        return repo.findByColor(color);
    }
    @GetMapping("/tshirt/size/{size}") //get by size
    public List<Tshirt> getTshirtBySize(@PathVariable String size) {
        return repo.findBySize(size);
    }
    @PostMapping("/tshirt") //create
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt(@RequestBody Tshirt tshirt) {
        return repo.save(tshirt);
    }

    @PostMapping("/tshirt/id/{id}") //update
    @ResponseStatus(HttpStatus.OK)
    public void updateTshirt(@PathVariable Integer id, @RequestBody Tshirt tshirt) {
        Optional<Tshirt> existingTshirt = repo.findById(id);
        if (existingTshirt.isPresent()) {
            tshirt.setTshirtId(id);
            repo.save(tshirt);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tshirt with id " + id + " not found.");
        }
    }

    @DeleteMapping("/tshirt/id/{id}") //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        Optional<Tshirt> existingCustomer = repo.findById(id);
        if (existingCustomer.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " not found.");
        }
    }
}
