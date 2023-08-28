package com.company.gamestore.service;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.FeeRepository;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.repository.TaxRepository;
import com.company.gamestore.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private InvoiceRepository invoiceRepository;
    private TaxRepository taxRepository;
    private FeeRepository feeRepository;


    @Autowired
    public ServiceLayer(InvoiceRepository invoiceRepository, TaxRepository taxRepository,FeeRepository feeRepository) {
        this.invoiceRepository = invoiceRepository;
        this.taxRepository = taxRepository;
        this.feeRepository = feeRepository;
    }


    //Create
    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel) {

        // Persist Invoice
        Invoice invoice = new Invoice();
        invoice.setName(viewModel.getName());
        invoice.setStreet(viewModel.getStreet());
        invoice.setCity(viewModel.getCity());
        invoice.setState(viewModel.getState());
        invoice.setZipcode(viewModel.getZipcode());
        invoice.setItemtype(viewModel.getItem_type());
        invoice.setItemId(viewModel.getItem_id());
        invoice.setUnit_price(viewModel.getUnit_price());
        invoice.setQuantity(viewModel.getQuantity());

        InvoiceViewModel ivm = buildInvoiceViewModel(invoice);

        invoice.setSubtotal(ivm.getSubtotal());
        invoice.setTax(ivm.getTax());
        invoice.setProcessing_fee(ivm.getProcessing_fee());
        invoice.setTotal(ivm.getTotal());

        invoice = invoiceRepository.save(invoice);
        viewModel.setInvoiceId(invoice.getInvoiceId());

        return viewModel;
    }

    //Read By Id
    public InvoiceViewModel findInvoice(int id) {

        Optional<Invoice> invoice = invoiceRepository.findById(id);

        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()) : null;
    }

    //Read all
    public List<InvoiceViewModel> findAllInvoices() {

        List<Invoice> invoiceList = invoiceRepository.findAll();

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }

        return ivmList;
    }

    //Need the method to find invoice by state


    //Build view model
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

        //Bussiness Logic-here or most like likely build view model

        //Below is checking requirements/ quantity,type,etc
        //String itemType = invoice.getItem_type().toLowerCase();

        //if (!"game".equals(itemType) && !"t-shirt".equals(itemType) && !"console".equals(itemType))  {
        //    throw new IllegalArgumentException("Invalid item type");
        //}

        //Check if item type, item id, quantity all valid
        //if(feeRepository.findByProductType(invoice.getItemtype()) == null){
        //    throw new IllegalArgumentException("Invalid item type");
        //}
        //if(console/game/tshirt repo to check valid id and quantitty)



        //Get tax
        Optional<Tax> state = taxRepository.findByState(invoice.getState());
        BigDecimal stateTax = BigDecimal.valueOf(0);

        if (state.isPresent()) {
            stateTax = state.get().getRate();
        }

        //Get processing fee
        Optional<Fee> type = feeRepository.findByProductType(invoice.getItemtype());
        BigDecimal processingFee = BigDecimal.valueOf(0);

        if (type.isPresent()) {
            processingFee = type.get().getFee();
        }

        //Calculations
        BigDecimal subtotal = BigDecimal.valueOf(0);
        if (invoice != null && invoice.getUnit_price() != null) {
            subtotal = invoice.getUnit_price().multiply(new BigDecimal(invoice.getQuantity()));
        }

        if(invoice.getQuantity() > 10){
            processingFee = processingFee.add(new BigDecimal(15.49));
        }

        BigDecimal total = subtotal.multiply(stateTax);
        total = total.add(processingFee);

        // Assemble the InvoiceViewModel
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(invoice.getInvoiceId());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZipcode(invoice.getZipcode());
        ivm.setItem_type(invoice.getItemtype());
        ivm.setItem_id(invoice.getItemId());
        ivm.setUnit_price(invoice.getUnit_price());
        ivm.setQuantity(invoice.getQuantity());

        ivm.setSubtotal(subtotal);
        ivm.setTax(stateTax);
        ivm.setProcessing_fee(processingFee);
        ivm.setTotal(total);

        // Return the InvoiceViewModel
        return ivm;
    }


}