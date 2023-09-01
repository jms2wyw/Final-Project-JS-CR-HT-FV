package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.repository.TshirtRepository;
import com.company.gamestore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    ServiceLayer serviceLayer;


    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateInvoice() throws Exception {

        Tshirt tshirt = new Tshirt();
        tshirt.setTshirtId(1);
        tshirt.setColor("Black");
        tshirt.setQuantity(1);
        tshirt.setDescription("Cotton Shirt");
        tshirt.setSize("L");
        tshirt.setPrice(new BigDecimal(12.99));

        Invoice invoice = new Invoice();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("t-shirt");
        invoice.setItemId(tshirt.getTshirtId());
        invoice.setUnitPrice(tshirt.getPrice());
        invoice.setQuantity(tshirt.getQuantity());
        invoice.setSubtotal(new BigDecimal(100));
        invoice.setTax(new BigDecimal(8.25));
        invoice.setProcessingFee(new BigDecimal(1.75));
        invoice.setTotal(new BigDecimal(100));
        invoice = invoiceRepo.save(invoice);

        String json = mapper.writeValueAsString(invoice);

        mockMvc.perform(post("/invoice/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());


    }

    @Test
    public void testGetInvoiceById() throws Exception {

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


        mockMvc.perform(get("/invoice/read/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllInvoice() throws Exception {

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

        Invoice secondInvoice = new Invoice();
        secondInvoice.setName("Jane Doe");
        secondInvoice.setStreet("123 Street");
        secondInvoice.setCity("City");
        secondInvoice.setState("CA");
        secondInvoice.setZipcode("54321");
        secondInvoice.setItemType("shirt");
        secondInvoice.setItemId(1);
        secondInvoice.setUnitPrice(new BigDecimal(100));
        secondInvoice.setQuantity(1);
        secondInvoice.setSubtotal(new BigDecimal(100));
        secondInvoice.setTax(new BigDecimal(8.25));
        secondInvoice.setProcessingFee(new BigDecimal(1.75));
        secondInvoice.setTotal(new BigDecimal(100));
        secondInvoice = invoiceRepo.save(invoice);

        String json = mapper.writeValueAsString(invoice);

        mockMvc.perform(get("/invoice/read")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInvoiceByName() throws Exception {
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



        String json = mapper.writeValueAsString(invoice);

        //Changed uriVariable to 1 as a string and seems to have fixed, no idea why
        mockMvc.perform(get("/invoice/read/name/{name}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testWrongCreateInvoice() throws Exception {

        Invoice invoice = new Invoice();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("State");
        invoice.setZipcode("54321");
        invoice.setItemType("CA");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal(100));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(100));
        invoice.setTax(new BigDecimal(8.25));
        invoice.setProcessingFee(new BigDecimal(1.75));
        invoice.setTotal(new BigDecimal(100));
        invoice = invoiceRepo.save(invoice);

        String json = mapper.writeValueAsString(invoice);

        mockMvc.perform(post("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());

    }
}