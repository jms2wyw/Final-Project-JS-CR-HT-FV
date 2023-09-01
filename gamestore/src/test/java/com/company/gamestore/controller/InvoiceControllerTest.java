package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.*;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewModel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.plaf.nimbus.State;
import java.math.BigDecimal;

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
    private TshirtRepository tshirtRepo;

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TaxRepository taxRepo;

    @Autowired
    private FeeRepository feeRepo;

    @Autowired
    ServiceLayer serviceLayer;


    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testCreateInvoice() throws Exception {
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setTshirtId(1);
        tshirt1.setSize("M");
        tshirt1.setColor("Red");
        tshirt1.setDescription("Short-sleeve");
        tshirt1.setPrice(new BigDecimal("19.99"));
        tshirt1.setQuantity(10);
        tshirt1 = tshirtRepo.save(tshirt1);


        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("t-shirt");
        invoice.setItemId(tshirt1.getTshirtId());
        invoice.setQuantity(1);

        String json = mapper.writeValueAsString(invoice);

        mockMvc.perform(post("/invoice/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());


    }


    @Test
    public void testGetInvoiceById() throws Exception {

        Tshirt tshirt1 = new Tshirt();
        tshirt1.setTshirtId(1);
        tshirt1.setSize("M");
        tshirt1.setColor("Red");
        tshirt1.setDescription("Short-sleeve");
        tshirt1.setPrice(new BigDecimal("19.99"));
        tshirt1.setQuantity(10);
        tshirt1 = tshirtRepo.save(tshirt1);


        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("t-shirt");
        invoice.setItemId(tshirt1.getTshirtId());
        invoice.setQuantity(1);
        invoice = serviceLayer.saveInvoice(invoice);

        String json = mapper.writeValueAsString(invoice);

        mockMvc.perform(get("/invoice/read/{id}", 1))

                .andExpect(status().isOk());


    }


    @Test
    public void testGetAllInvoice() throws Exception {

        Tshirt tshirt1 = new Tshirt();
        tshirt1.setTshirtId(1);
        tshirt1.setSize("M");
        tshirt1.setColor("Red");
        tshirt1.setDescription("Short-sleeve");
        tshirt1.setPrice(new BigDecimal("19.99"));
        tshirt1.setQuantity(10);
        tshirt1 = tshirtRepo.save(tshirt1);


        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("t-shirt");
        invoice.setItemId(tshirt1.getTshirtId());
        invoice.setQuantity(1);
        invoice = serviceLayer.saveInvoice(invoice);

        invoice = new InvoiceViewModel();
        invoice.setName("Jane Doe");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("TX");
        invoice.setZipcode("54321");
        invoice.setItemType("t-shirt");
        invoice.setItemId(tshirt1.getTshirtId());
        invoice.setQuantity(1);
        invoice = serviceLayer.saveInvoice(invoice);

        String json = mapper.writeValueAsString(invoice);

        mockMvc.perform(get("/invoice/read")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInvoiceByName() throws Exception {
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setTshirtId(1);
        tshirt1.setSize("M");
        tshirt1.setColor("Red");
        tshirt1.setDescription("Short-sleeve");
        tshirt1.setPrice(new BigDecimal("19.99"));
        tshirt1.setQuantity(10);
        tshirt1 = tshirtRepo.save(tshirt1);


        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("t-shirt");
        invoice.setItemId(tshirt1.getTshirtId());
        invoice.setQuantity(1);
        invoice = serviceLayer.saveInvoice(invoice);



        String json = mapper.writeValueAsString(invoice);

        mockMvc.perform(get("/invoice/read/name/{name}", "John Smith")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testWrongCreateInvoice() throws Exception {

        Tshirt tshirt1 = new Tshirt();
        tshirt1.setTshirtId(1);
        tshirt1.setSize("M");
        tshirt1.setColor("Red");
        tshirt1.setDescription("Short-sleeve");
        tshirt1.setPrice(new BigDecimal("19.99"));
        tshirt1.setQuantity(10);
        tshirt1 = tshirtRepo.save(tshirt1);


        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("John Smith");
        invoice.setStreet("123 Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("t-shirt");
        invoice.setItemId(tshirt1.getTshirtId());
        invoice.setQuantity(1);
        invoice = serviceLayer.saveInvoice(invoice);

        String json = mapper.writeValueAsString(invoice);

        mockMvc.perform(post("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());

    }


}
