package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;

    @javax.validation.constraints.NotNull(message = "Title cannot be null")
    private String title;
    @Column(name = "esrb_rating")
    @javax.validation.constraints.NotNull(message = "ESRB Rating cannot be null")
    private String esrbRating;
    @javax.validation.constraints.NotNull(message = "Description cannot be null")
    private String description;
    @javax.validation.constraints.NotNull(message = "Price cannot be null")
    private BigDecimal price;
    @javax.validation.constraints.NotNull(message = "Studio cannot be null")
    private String studio;
    private int quantity;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return getGameId() == game.getGameId() &&
                getQuantity() == game.getQuantity() &&
                Objects.equals(getTitle(), game.getTitle()) &&
                Objects.equals(getEsrbRating(), game.getEsrbRating()) &&
                Objects.equals(getDescription(), game.getDescription()) &&
                Objects.equals(getPrice(), game.getPrice()) &&
                Objects.equals(getStudio(), game.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getGameId(),
                getTitle(),
                getEsrbRating(),
                getDescription(),
                getPrice(),
                getStudio(),
                getQuantity()
        );
    }
}
