package com.company.gamestore.controller;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.service.TshirtServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
@RestController
@RequestMapping("/tshirts")
public class TshirtController {
    @Autowired
    private TshirtServiceLayer tshirtServiceLayer;

    @PostMapping
    public ResponseEntity<Tshirt> createTshirt(@RequestBody Tshirt tshirt) {
        return new ResponseEntity<>(tshirtServiceLayer.createTshirt(tshirt), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tshirt>> getAllTshirts() {
        return new ResponseEntity<>(tshirtServiceLayer.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tshirt> getTshirtById(@PathVariable int id) {
        Tshirt tshirt = tshirtServiceLayer.findById(id);
        if (tshirt == null) {
            throw new NotFoundException("T-shirt not found");
        }
        return new ResponseEntity<>(tshirt, HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Tshirt>> getTshirtsByColor(@PathVariable String color) {
        return new ResponseEntity<>(tshirtServiceLayer.findByColor(color), HttpStatus.OK);
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<Tshirt>> getTshirtsBySize(@PathVariable String size) {
        return new ResponseEntity<>(tshirtServiceLayer.findBySize(size), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tshirt> updateTshirt(@PathVariable int id, @RequestBody Tshirt tshirt) {
        tshirt.setTshirtId(id);
        return new ResponseEntity<>(tshirtServiceLayer.updateTshirt(tshirt), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTshirt(@PathVariable int id) {
        tshirtServiceLayer.deleteById(id);
        return new ResponseEntity<>("Deleted Tshirt id : " + id, HttpStatus.OK);
    }
}
