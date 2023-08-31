package com.company.gamestore.service;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class TshirtServiceLayer {
    @Autowired
    private TshirtRepository tshirtRepository;

    public Tshirt createTshirt(Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }
    public List<Tshirt> findAll() {
        return tshirtRepository.findAll();
    }

    public Tshirt findById(int id) {
        return tshirtRepository.findById(id).orElseThrow(() -> new NotFoundException("T-shirt not found"));
    }

    public List<Tshirt> findByColor(String color) {
        return tshirtRepository.findByColor(color);
    }

    public List<Tshirt> findBySize(String size) {
        return tshirtRepository.findBySize(size);
    }

    public Tshirt updateTshirt(Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }

    public void deleteById(int id) {
        tshirtRepository.deleteById(id);
    }
}