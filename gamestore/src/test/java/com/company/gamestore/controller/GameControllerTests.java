package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;

    private List<Game> gameList = new ArrayList<>();

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        Game videoGame = new Game();
        videoGame.setGameId(1);
        videoGame.setTitle("Slay the Spire");
        videoGame.setDescription("Card game about climbing the spire");
        videoGame.setStudio("Mega Crit Games");
        videoGame.setEsrbRating("Teen");
        videoGame.setPrice(new BigDecimal("24.99"));

        Game otherGame = new Game();
        otherGame.setGameId(2);
        otherGame.setTitle("Minecraft");
        otherGame.setDescription("Open world block building game");
        otherGame.setStudio("Mojang");
        otherGame.setEsrbRating("Everyone");
        otherGame.setPrice(new BigDecimal("34.99"));

        Game secondStudioGame = new Game();
        secondStudioGame.setGameId(3);
        secondStudioGame.setTitle("Minecraft Legends");
        secondStudioGame.setDescription("Weirder game based in Minecraft world");
        secondStudioGame.setStudio("Mojang");
        secondStudioGame.setEsrbRating("Everyone");
        secondStudioGame.setPrice(new BigDecimal("24.99"));

        gameList.add(videoGame);
        gameList.add(otherGame);
        gameList.add(secondStudioGame);
    }


    @Test
    public void PostGame() throws Exception {
        String inputJson = mapper.writeValueAsString(gameList.get(0));

        //Mock server calls
        mockMvc.perform(post("/games")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void UpdateGame() throws Exception {
        String inputJson = mapper.writeValueAsString(gameList.get(0));

        //Mock server calls
        mockMvc.perform(put("/games")  // Removed the '/1', to match the @PutMapping in the controller
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk()); // You're expecting 201 Created but your controller is set to return 200 OK
    }


    @Test
    public void DeleteGame() throws Exception {
        mockMvc.perform(delete("/games/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFindById() throws Exception {
        String expected = mapper.writeValueAsString(gameList.get(0));
        System.out.println(expected);
        System.out.println(status());

        mockMvc.perform(post("/games")
                .content(expected)
                .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(get("/games/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldReturnMissing() throws Exception {

        //Mock server calls
        mockMvc.perform(get("/games/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    //Not sure on this, they should be returning a list - not game (same for studio)
    @Test
    public void shouldFindByRating() throws Exception {
        String output = mapper.writeValueAsString(gameList.get(0));

        //Mock server calls
        mockMvc.perform(get("/games/ratings/Teen"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
