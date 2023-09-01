package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    boolean existsByGameId(int itemId);

    List<Game> findByStudio(String studio);

    List<Game> findByesrbRating(String rating);

    Optional<Game> findByTitle(String title);
}
