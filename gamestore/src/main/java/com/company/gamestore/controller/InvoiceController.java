package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    InvoiceRepository repo;

    //Create Invoice
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

    //Read
    @GetMapping("/read/{id}")
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.findInvoice(id);
    }

    //Read all
    @GetMapping("/read")
    public List<InvoiceViewModel> getInvoices() {
        return serviceLayer.findAllInvoices();
    }

    //Read by customer name
    @GetMapping("/read/name/{name}")
    public List<InvoiceViewModel> getCustomersByName(@PathVariable String name) {
        return serviceLayer.findInvoiceByName(name);
    }
}
