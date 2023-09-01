package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private InvoiceRepository invoiceRepository;

    private TshirtRepository tshirtRepository;

    private ConsoleRepository consoleRepository;

    private GameRepository gameRepository;

    private TaxRepository taxRepository;
    private FeeRepository feeRepository;


    @Autowired
    public ServiceLayer(InvoiceRepository invoiceRepository, TshirtRepository tshirtRepository, ConsoleRepository consoleRepository, GameRepository gameRepository, TaxRepository taxRepository,FeeRepository feeRepository) {
        this.invoiceRepository = invoiceRepository;
        this.tshirtRepository = tshirtRepository;
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
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
        invoice.setItemType(viewModel.getItemType());
        invoice.setItemId(viewModel.getItem_id());
        invoice.setQuantity(viewModel.getQuantity());

        InvoiceViewModel ivm = buildInvoiceViewModel(invoice);

        viewModel.setUnitPrice(ivm.getUnitPrice());
        viewModel.setSubtotal(ivm.getSubtotal());
        viewModel.setTax(ivm.getTax());
        viewModel.setProcessingFee(ivm.getProcessingFee());
        viewModel.setTotal(ivm.getTotal());

        invoice.setUnitPrice(ivm.getUnitPrice());
        invoice.setSubtotal(ivm.getSubtotal());
        invoice.setTax(ivm.getTax());
        invoice.setProcessingFee(ivm.getProcessingFee());
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

    public List<InvoiceViewModel> findInvoiceByName(String name) {

        List<Invoice> invoiceList = invoiceRepository.findByName(name);

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }

        return ivmList;

    }


    //Build view model
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

        // Assemble the InvoiceViewModel
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(invoice.getInvoiceId());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZipcode(invoice.getZipcode());
        ivm.setItemType(invoice.getItemType());
        ivm.setItemId(invoice.getItemId());
        ivm.setQuantity(invoice.getQuantity());

        //Business Logic
        //Validate Item Type
        String itemType = invoice.getItemType().toLowerCase();

        if (!itemType.equals("game") && !itemType.equals("t-shirt") && !itemType.equals("console")){
            throw new IllegalArgumentException("Invalid item type");

        }

        //Validate itemId
        int itemId = invoice.getItemId();

        if(!gameRepository.existsByGameId(itemId) && !tshirtRepository.existsByTshirtId(itemId) && !consoleRepository.existsByConsoleId(itemId)){
            throw new IllegalArgumentException("Invalid item id");
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        //Validate quantity and update quantity
        if(itemType.equals("game")){
            Optional<Game> game = gameRepository.findById(itemId);

            if(game.get().getQuantity() < invoice.getQuantity()){
                throw new IllegalArgumentException("Invalid quantity");
            }else{
                int updateQuantity = game.get().getQuantity()-invoice.getQuantity();
                game.get().setQuantity(updateQuantity);

                //Calculate Subtotal
                subtotal = game.get().getPrice().multiply(new BigDecimal(invoice.getQuantity()));
                BigDecimal subtotalFormatted = subtotal.setScale(2, RoundingMode.UP);
                ivm.setUnitPrice(game.get().getPrice());
                ivm.setSubtotal(subtotalFormatted);

            }

        }else if(itemType.equals("t-shirt")){
            Optional<Tshirt> tshirt = tshirtRepository.findById(itemId);

            if(tshirt.get().getQuantity() < invoice.getQuantity()){
                throw new IllegalArgumentException("Invalid quantity");
            }else{
                int updateQuantity = tshirt.get().getQuantity()-invoice.getQuantity();
                tshirt.get().setQuantity(updateQuantity);

                //Calculate Subtotal
                subtotal = tshirt.get().getPrice().multiply(new BigDecimal(invoice.getQuantity()));
                BigDecimal subtotalFormatted = subtotal.setScale(2, RoundingMode.UP);
                ivm.setUnitPrice(tshirt.get().getPrice());
                ivm.setSubtotal(subtotalFormatted);
            }
        }else if(itemType.equals("console")){
            Optional<Console> console = consoleRepository.findById(itemId);

            if(console.get().getQuantity() < invoice.getQuantity()){
                throw new IllegalArgumentException("Invalid quantity");
            }else{
                int updateQuantity = console.get().getQuantity()-invoice.getQuantity();
                console.get().setQuantity(updateQuantity);

                //Calculate Subtotal
                subtotal = console.get().getPrice().multiply(new BigDecimal(invoice.getQuantity()));
                BigDecimal subtotalFormatted = subtotal.setScale(2, RoundingMode.UP);
                ivm.setUnitPrice(console.get().getPrice());
                ivm.setSubtotal(subtotalFormatted);
            }
        }

        //Get tax
        Optional<Tax> state = taxRepository.findByState(invoice.getState());
        BigDecimal stateTax = BigDecimal.valueOf(0);

        if (state.isPresent()) {
            stateTax = state.get().getRate();
            stateTax = subtotal.multiply(stateTax); // Use the calculated subtotal
            ivm.setTax(stateTax);
        } else {
            throw new IllegalArgumentException("Invalid state");
        }

        //Get processing fees
        Optional<Fee> type = feeRepository.findByProductType(invoice.getItemType());
        BigDecimal processingFee = processingFee = type.get().getFee();

        if(invoice.getQuantity() > 10){
            processingFee = processingFee.add(new BigDecimal(15.49));
        }

        processingFee = processingFee.setScale(2, RoundingMode.UP);
        ivm.setProcessingFee(processingFee);

        //Total
        BigDecimal total = subtotal.add(ivm.getTax()); // Use ivm.getTax()
        total = total.add(ivm.getProcessingFee()); // Use ivm.getProcessingFee()
        total = total.setScale(2, RoundingMode.UP);
        ivm.setTotal(total);

        // Return the InvoiceViewModel
        return ivm;
    }


}