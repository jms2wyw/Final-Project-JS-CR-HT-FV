package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console {

    @Id
    @Column(name = "console_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int consoleId;

    @javax.validation.constraints.NotNull(message = "Model cannot be null")
    private String model;
    @javax.validation.constraints.NotNull(message = "Memory amount cannot be null")
    private String memory_amount;
    @javax.validation.constraints.NotNull(message = "Processor cannot be null")
    private String processor;
    @javax.validation.constraints.NotNull(message = "Price cannot be null")
    private BigDecimal price;
    @javax.validation.constraints.NotNull(message = "Quantity cannot be null")
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

    public String getMemory_amount() {
        return memory_amount;
    }

    public void setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Console)) return false;
        Console console = (Console) o;
        return getConsoleId() == console.getConsoleId() &&
                getQuantity() == console.getQuantity() &&
                Objects.equals(getModel(), console.getModel()) &&
                Objects.equals(getMemory_amount(), console.getMemory_amount()) &&
                Objects.equals(getProcessor(), console.getProcessor()) &&
                Objects.equals(getPrice(), console.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getConsoleId(),
                getModel(),
                getMemory_amount(),
                getProcessor(),
                getPrice(),
                getQuantity()
        );
    }
}