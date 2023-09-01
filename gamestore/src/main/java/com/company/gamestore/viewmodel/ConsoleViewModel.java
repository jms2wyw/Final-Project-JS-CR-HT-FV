package com.company.gamestore.viewmodel;

import com.company.gamestore.model.Console;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ConsoleViewModel implements Serializable {
    private int consoleId;
    @NotEmpty(message = "Model cannot be null")
    private String model;
    @NotEmpty(message = "Manufacturer cannot be null")
    private String manufacturer;
    @NotEmpty(message = "Memory amount cannot be null")
    private String memoryAmount;
    @NotEmpty(message = "Processor cannot be null")
    private String processor;
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;
    @NotNull(message = "Quantity cannot be null")
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
        if (!(o instanceof Console)) return false;
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