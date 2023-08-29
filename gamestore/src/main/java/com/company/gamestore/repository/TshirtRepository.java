package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TshirtRepository  extends JpaRepository<Tshirt, Integer> {
    //find by color
    List<Tshirt> findByColor(String color);
    //find by size
    List<Tshirt> findBySize(String size);


}
