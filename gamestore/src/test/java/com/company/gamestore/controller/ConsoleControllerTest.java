package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.service.ConsoleServiceLayer;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsoleController.class)
@AutoConfigureMockMvc
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleServiceLayer repo;

    private List<Console> consoleList = new ArrayList<>();

    private ObjectMapper mapper = new ObjectMapper();

    private ConsoleViewModel console1;

    @BeforeEach
    public void setup(){
        Console console = new Console();
        console.setConsoleId(1);
        console.setModel("1");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(100);
        console.setMemoryAmount("5GB");
        console.setManufacturer("Xbox");
        console.setProcessor("CPU");

        console1 = new ConsoleViewModel();
        console1.setConsoleId(2);
        console1.setModel("2");
        console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GB");
        console1.setManufacturer("Xbox");
        console1.setProcessor("CPU");

        Console console2 = new Console();
        console2.setConsoleId(3);
        console2.setModel("3");
        console2.setPrice(new BigDecimal("499.99"));
        console2.setQuantity(100);
        console2.setMemoryAmount("5GB");
        console2.setManufacturer("Sony");
        console2.setProcessor("CPU");


        consoleList.add(console);
        //consoleList.add(console1);
        consoleList.add(console2);
    }
    @Test
    public void addConsole() throws Exception {
        ConsoleViewModel console1 = new ConsoleViewModel();
        console1.setModel("7");
        console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GBb");
        console1.setManufacturer("Xbox");
        console1.setProcessor("CPU");
        ///console1 = repo.saveConsole(console1);
        String inputJson = mapper.writeValueAsString(console1);

        //Mock server calls
        mockMvc.perform(post("/console")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void updateConsole() throws Exception {
        ///ConsoleViewModel console1 = new ConsoleViewModel();
        ///console1.setConsoleId(5);
        console1.setModel("7");
        /***console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GBb");
        console1.setManufacturer("Xbox");
        console1.setProcessor("CPU");
        console1 = repo.saveConsole(console1);
        console1.setProcessor("Some");
        repo.saveConsole(console1);***/
        String inputJson = mapper.writeValueAsString(console1);

        //Mock server calls
        mockMvc.perform(put("/console")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }


    @Test
    public void deleteConsole() throws Exception {
        mockMvc.perform(delete("/console/1"))
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldFindConsoleById() throws Exception {

        //Mock server calls
        mockMvc.perform(get("/console/{id}", 1))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldFindAllConsoles() throws Exception {
        ConsoleViewModel console1 = new ConsoleViewModel();
        console1.setConsoleId(1);
        console1.setModel("7");
        console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GBb");
        console1.setManufacturer("Xbox");
        console1.setProcessor("CPU");
        console1 = repo.saveConsole(console1);

        console1 = new ConsoleViewModel();
        console1.setConsoleId(2);
        console1.setModel("9");
        console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GBb");
        console1.setManufacturer("Sony");
        console1.setProcessor("CPU");
        console1 = repo.saveConsole(console1);

        String inputJson = mapper.writeValueAsString(console1);
        mockMvc.perform(get("/consoles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                )
                .andExpect(status().isOk());
    }





    //Not sure on this, they should be returning a list - not game (same for studio)
    @Test
    public void shouldFindConsoleByManufacturer() throws Exception {
        ConsoleViewModel console1 = new ConsoleViewModel();
        console1.setConsoleId(1);
        console1.setModel("7");
        console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GBb");
        console1.setManufacturer("Xbox");
        console1.setProcessor("CPU");
        console1 = repo.saveConsole(console1);
        String inputJson = mapper.writeValueAsString(console1);
        //Mock server calls
        mockMvc.perform(get("/console/manufacturer/{manufacturer}","Xbox")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson)
                )
                .andExpect(status().isOk());
    }
}
