package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepo;

    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepo.deleteAll();

    }

    @Test
    public void shouldCreateInvoice() {

        //Arrange...
        Invoice invoice = new Invoice();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("game");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal(100));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(100));
        invoice.setTax(new BigDecimal(8.25));
        invoice.setProcessingFee(new BigDecimal(1.75));
        invoice.setTotal(new BigDecimal(100));
        invoice = invoiceRepo.save(invoice);

        // Act...
        Invoice foundInvoice = invoiceRepo.findById(invoice.getInvoiceId()).orElse(null);

        // Assert...
        assertEquals(invoice.getInvoiceId(), foundInvoice.getInvoiceId());
        assertEquals(invoice.getName(), foundInvoice.getName());
    }

    @Test
    public void shouldGetInvoiceById() {

        //Arrange...
        Invoice invoice = new Invoice();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("game");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal(100));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(100));
        invoice.setTax(new BigDecimal(8.25));
        invoice.setProcessingFee(new BigDecimal(1.75));
        invoice.setTotal(new BigDecimal(100));
        invoice = invoiceRepo.save(invoice);

        // Act...
        Invoice foundInvoice = invoiceRepo.findById(invoice.getInvoiceId()).orElse(null);

        // Assert...
        assertEquals(invoice.getInvoiceId(), foundInvoice.getInvoiceId());
        assertEquals(invoice.getName(), foundInvoice.getName());
    }

    @Test
    public void shouldGetAllInvoice() {

        //Arrange...
        Invoice invoice = new Invoice();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("game");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal(100));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(100));
        invoice.setTax(new BigDecimal(8.25));
        invoice.setProcessingFee(new BigDecimal(1.75));
        invoice.setTotal(new BigDecimal(100));
        invoice = invoiceRepo.save(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setName("John Smith");
        invoice2.setStreet("123 Street");
        invoice2.setCity("City");
        invoice2.setState("CA");
        invoice2.setZipcode("54321");
        invoice2.setItemType("shirt");
        invoice2.setItemId(1);
        invoice2.setUnitPrice(new BigDecimal(100));
        invoice2.setQuantity(1);
        invoice2.setSubtotal(new BigDecimal(100));
        invoice2.setTax(new BigDecimal(8.25));
        invoice2.setProcessingFee(new BigDecimal(1.75));
        invoice2.setTotal(new BigDecimal(100));
        invoice2 = invoiceRepo.save(invoice2);

        // Act...
        //Act...
        List<Invoice> invoiceList = invoiceRepo.findAll();

        //Assert...
        assertEquals(invoiceList.size(), 2);
    }

    @Test
    public void shouldFindInvoiceByName() {

        //Arrange...
        Invoice invoice = new Invoice();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("game");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal(100));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(100));
        invoice.setTax(new BigDecimal(8.25));
        invoice.setProcessingFee(new BigDecimal(1.75));
        invoice.setTotal(new BigDecimal(100));
        invoice = invoiceRepo.save(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setName("John Smith");
        invoice2.setStreet("123 Street");
        invoice2.setCity("City");
        invoice2.setState("CA");
        invoice2.setZipcode("54321");
        invoice2.setItemType("shirt");
        invoice2.setItemId(1);
        invoice2.setUnitPrice(new BigDecimal(100));
        invoice2.setQuantity(1);
        invoice2.setSubtotal(new BigDecimal(100));
        invoice2.setTax(new BigDecimal(8.25));
        invoice2.setProcessingFee(new BigDecimal(1.75));
        invoice2.setTotal(new BigDecimal(100));
        invoice2 = invoiceRepo.save(invoice2);

        //Act...
        List<Invoice> findInvoice = invoiceRepo.findByName(invoice.getName());

        //Assert...
        assertEquals(2,findInvoice.size());
    }




}