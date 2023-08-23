package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private int invoiceId;

    @javax.validation.constraints.NotNull(message = "Name cannot be null")
    private String name;
    @javax.validation.constraints.NotNull(message = "Street cannot be null")
    private String street;
    @javax.validation.constraints.NotNull(message = "City cannot be null")
    private String city;
    @javax.validation.constraints.NotNull(message = "State cannot be null")
    private String state;
    @javax.validation.constraints.NotNull(message = "Zipcode cannot be null")
    private String zipcode;
    @javax.validation.constraints.NotNull(message = "Item type cannot be null")
    private String item_type;
    @javax.validation.constraints.NotNull(message = "Item id cannot be null")
    private int item_id;
    @javax.validation.constraints.NotNull(message = "Unit price cannot be null")
    private BigDecimal unit_price;
    @javax.validation.constraints.NotNull(message = "Quantity cannot be null")
    private int quantity;
    @javax.validation.constraints.NotNull(message = "Subtotal cannot be null")
    private BigDecimal subtotal;
    @javax.validation.constraints.NotNull(message = "Tax cannot be null")
    private BigDecimal tax;
    @javax.validation.constraints.NotNull(message = "Processing fee cannot be null")
    private BigDecimal processing_fee;
    @javax.validation.constraints.NotNull(message = "Total cannot be null")
    private BigDecimal total;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(BigDecimal processing_fee) {
        this.processing_fee = processing_fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getItem_id() == invoice.getItem_id() &&
                getQuantity() == invoice.getQuantity() &&
                Objects.equals(getName(), invoice.getName()) &&
                Objects.equals(getStreet(), invoice.getStreet()) &&
                Objects.equals(getCity(), invoice.getCity()) &&
                Objects.equals(getState(), invoice.getState()) &&
                Objects.equals(getZipcode(), invoice.getZipcode()) &&
                Objects.equals(getItem_type(), invoice.getItem_type()) &&
                Objects.equals(getUnit_price(), invoice.getUnit_price()) &&
                Objects.equals(getSubtotal(), invoice.getSubtotal()) &&
                Objects.equals(getTax(), invoice.getTax()) &&
                Objects.equals(getProcessing_fee(), invoice.getProcessing_fee()) &&
                Objects.equals(getTotal(), invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getInvoiceId(),
                getName(),
                getStreet(),
                getCity(),
                getState(),
                getZipcode(),
                getItem_type(),
                getItem_id(),
                getUnit_price(),
                getQuantity(),
                getSubtotal(),
                getTax(),
                getProcessing_fee(),
                getTotal()
        );
    }
}
