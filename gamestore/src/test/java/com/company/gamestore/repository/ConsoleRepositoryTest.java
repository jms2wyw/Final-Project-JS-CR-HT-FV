package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository repo;

    @BeforeEach
    public void setUp() throws Exception{
        repo.deleteAll();
    }

    @Test
    public void shouldAddConsole(){
        Console console = new Console();
        console.setConsoleId(1);
        console.setModel("1");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(100);
        console.setMemoryAmount("5GB");
        console.setManufacturer("Xbox");
        console.setProcessor("CPU");

        console = repo.save(console);

        Optional<Console> console1 = repo.findById(console.getConsoleId());
        assertEquals(console1.get(),console);
    }

    @Test
    public void shouldGetConsoleById(){
        Console console = new Console();
        console.setConsoleId(2);
        console.setModel("2");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(100);
        console.setMemoryAmount("5GB");
        console.setManufacturer("Xbox");
        console.setProcessor("CPU");

        console = repo.save(console);

        Console console1 = new Console();
        console1.setConsoleId(3);
        console1.setModel("3");
        console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GB");
        console1.setManufacturer("Xbox");
        console1.setProcessor("CPU");

        console1 = repo.save(console1);

        Optional<Console> consoleFound = repo.findById(console.getConsoleId());
        assertEquals(consoleFound.get(),console);
    }

    @Test
    public void shouldGetAllConsoles(){
        Console console = new Console();
        console.setConsoleId(3);
        console.setModel("3");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(100);
        console.setMemoryAmount("5GB");
        console.setManufacturer("Xbox");
        console.setProcessor("CPU");

        console = repo.save(console);

        Console console1 = new Console();
        console1.setConsoleId(4);
        console1.setModel("4");
        console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GB");
        console1.setManufacturer("Xbox");
        console1.setProcessor("CPU");

        console1 = repo.save(console1);

        List<Console> consoles = repo.findAll();
        assertEquals(consoles.size(),2);
    }

    @Test
    public void shouldUpdateConsole(){
        Console console = new Console();
        console.setConsoleId(6);
        console.setModel("6");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(100);
        console.setMemoryAmount("5GB");
        console.setManufacturer("Xbox");
        console.setProcessor("CPU");

        console = repo.save(console);

        console.setModel("8");

        repo.save(console);

        Optional<Console> console1 = repo.findById(console.getConsoleId());

        assertEquals(console1.get(), console);

    }

    @Test
    public void shouldDeleteConsoleById(){
        Console console = new Console();
        console.setConsoleId(7);
        console.setModel("7");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(100);
        console.setMemoryAmount("5GB");
        console.setManufacturer("Xbox");
        console.setProcessor("CPU");

        console = repo.save(console);
        Optional<Console> console1 = repo.findById(console.getConsoleId());

        assertEquals(console1.get(), console);
        repo.deleteById(console.getConsoleId());
        console1 = repo.findById(console.getConsoleId());
        assertFalse(console1.isPresent());

    }

    @Test
    public void shouldGetConsoleByManfuacturer(){
        Console console = new Console();
        console.setConsoleId(7);
        console.setModel("7");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(100);
        console.setMemoryAmount("5GB");
        console.setManufacturer("Xbox");
        console.setProcessor("CPU");

        console = repo.save(console);

        Console console1 = new Console();
        console1.setConsoleId(3);
        console1.setModel("3");
        console1.setPrice(new BigDecimal("499.99"));
        console1.setQuantity(100);
        console1.setMemoryAmount("5GB");
        console1.setManufacturer("Sony");
        console1.setProcessor("CPU");

        console1 = repo.save(console1);

        List<Console> console2 = repo.findByManufacturer("Sony");
        assertEquals(console2.size(),1);

    }
}

