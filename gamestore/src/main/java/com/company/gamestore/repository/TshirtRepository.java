package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TshirtRepository extends JpaRepository<Tshirt, Integer> {
    List<Tshirt> findByColor(String color);
    List<Tshirt> findBySize(String size);
    boolean existsByTshirtId(int itemId);
}
