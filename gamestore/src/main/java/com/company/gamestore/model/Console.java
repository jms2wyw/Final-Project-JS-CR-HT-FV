package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console implements Serializable {

    @Id
    @Column(name = "console_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int consoleId;


    private String model;


    private String manufacturer;

    private String memoryAmount;

    private String processor;

    private BigDecimal price;

    private int quantity;

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memory_amount) {
        this.memoryAmount = memory_amount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;

        return getConsoleId() == console.getConsoleId() &&
                getQuantity() == console.getQuantity() &&
                Objects.equals(getModel(), console.getModel()) &&
                Objects.equals(getMemoryAmount(), console.getMemoryAmount()) &&
                Objects.equals(getProcessor(), console.getProcessor()) &&
                Objects.equals(getPrice(), console.getPrice())&&
                Objects.equals(getManufacturer(), console.getManufacturer());

    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getConsoleId(),
                getModel(),
                getMemoryAmount(),
                getProcessor(),
                getPrice(),
                getQuantity(),
                getManufacturer()
        );

    }
}
