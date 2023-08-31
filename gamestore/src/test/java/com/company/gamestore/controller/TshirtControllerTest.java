package com.company.gamestore.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.company.gamestore.controller.TshirtController;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.service.TshirtServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TshirtServiceLayer tshirtServiceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    List<Tshirt> tshirtList;

    @BeforeEach
    void setUp() {
        // Mock data
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setTshirtId(1);
        tshirt1.setSize("M");
        tshirt1.setColor("Red");
        tshirt1.setDescription("Short-sleeve");
        tshirt1.setPrice(new BigDecimal("19.99"));
        tshirt1.setQuantity(10);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setTshirtId(2);
        tshirt2.setSize("L");
        tshirt2.setColor("Blue");
        tshirt2.setDescription("Long-sleeve");
        tshirt2.setPrice(new BigDecimal("29.99"));
        tshirt2.setQuantity(5);

        tshirtList = Arrays.asList(tshirt1, tshirt2);
    }


    @Test
    public void shouldReturnAllTshirts() throws Exception {
        when(tshirtServiceLayer.findAll()).thenReturn(tshirtList);

        mockMvc.perform(get("/tshirts"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(tshirtList)));
    }

    @Test
    public void shouldReturnTshirtById() throws Exception {
        when(tshirtServiceLayer.findById(1)).thenReturn(tshirtList.get(0));

        mockMvc.perform(get("/tshirts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(tshirtList.get(0))));
    }

    @Test
    public void shouldReturnTshirtsByColor() throws Exception {
        when(tshirtServiceLayer.findByColor("Red")).thenReturn(Arrays.asList(tshirtList.get(0)));

        mockMvc.perform(get("/tshirts/color/Red"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(tshirtList.get(0)))));
    }

    @Test
    public void shouldReturnTshirtsBySize() throws Exception {
        when(tshirtServiceLayer.findBySize("M")).thenReturn(Arrays.asList(tshirtList.get(0)));

        mockMvc.perform(get("/tshirts/size/M"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(tshirtList.get(0)))));
    }

    @Test
    public void shouldCreateNewTshirt() throws Exception {
        Tshirt inputTshirt = new Tshirt();
        inputTshirt.setSize("XL");
        inputTshirt.setColor("Green");
        inputTshirt.setDescription("Short-sleeve");
        inputTshirt.setPrice(new BigDecimal("19.99"));
        inputTshirt.setQuantity(20);

        Tshirt expectedOutputTshirt = new Tshirt();
        expectedOutputTshirt.setTshirtId(3);
        expectedOutputTshirt.setSize("XL");
        expectedOutputTshirt.setColor("Green");
        expectedOutputTshirt.setDescription("Short-sleeve");
        expectedOutputTshirt.setPrice(new BigDecimal("19.99"));
        expectedOutputTshirt.setQuantity(20);

        when(tshirtServiceLayer.createTshirt(inputTshirt)).thenReturn(expectedOutputTshirt);

        mockMvc.perform(
                        post("/tshirts")
                                .content(mapper.writeValueAsString(inputTshirt))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(expectedOutputTshirt)));
    }

    @Test
    public void shouldUpdateTshirt() throws Exception {
        Tshirt updatedTshirt = new Tshirt();
        updatedTshirt.setTshirtId(1);
        updatedTshirt.setSize("M");
        updatedTshirt.setColor("Purple");
        updatedTshirt.setDescription("Long-sleeve");
        updatedTshirt.setPrice(new BigDecimal("24.99"));
        updatedTshirt.setQuantity(5);

        mockMvc.perform(
                        put("/tshirts/1")
                                .content(mapper.writeValueAsString(updatedTshirt))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(tshirtServiceLayer).updateTshirt(updatedTshirt);
    }

    @Test
    public void shouldDeleteTshirt() throws Exception {
        mockMvc.perform(delete("/tshirts/1"))
                .andExpect(status().isOk());

        verify(tshirtServiceLayer).deleteById(1);
    }
}
