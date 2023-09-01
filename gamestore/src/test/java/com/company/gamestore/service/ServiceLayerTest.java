package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewModel.InvoiceViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    private InvoiceRepository invoiceRepository;
    private TshirtRepository tshirtRepository;
    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private TaxRepository taxRepository;
    private FeeRepository feeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        setUpInvoiceRepositoryMock();
        setUpTshirtRepositoryMock();
        setUpConsoleRepositoryMock();
        setUpGameRepositoryMock();
        setUpTaxRepositoryMock();
        setUpFeeRepositoryMock();

        service = new ServiceLayer(invoiceRepository, tshirtRepository, consoleRepository, gameRepository, taxRepository, feeRepository);

    }

    // Helper methods
    private void setUpInvoiceRepositoryMock(){
        invoiceRepository = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setName("Francisco");
        invoice.setStreet("Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("game");
        invoice.setItemId(1);
        invoice.setQuantity(1);

        Invoice invoice2 = new Invoice();
        invoice2.setName("Francisco");
        invoice2.setStreet("Street");
        invoice2.setCity("City");
        invoice2.setState("CA");
        invoice2.setZipcode("54321");
        invoice2.setItemType("game");
        invoice2.setItemId(1);
        invoice2.setQuantity(1);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceRepository).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(invoiceList).when(invoiceRepository).findByItemId(1);
        doReturn(invoiceList).when(invoiceRepository).findAll();

    }

    private void setUpTshirtRepositoryMock(){
        tshirtRepository = mock(TshirtRepository.class);
        Tshirt tshirt = new Tshirt();
        tshirt.setTshirtId(1);
        tshirt.setSize("L");
        tshirt.setColor("Black");
        tshirt.setDescription("Cotton");
        tshirt.setQuantity(100);
        tshirt.setPrice(new BigDecimal(10));

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setSize("L");
        tshirt2.setColor("Black");
        tshirt2.setDescription("Cotton");
        tshirt2.setQuantity(100);
        tshirt2.setPrice(new BigDecimal(10));

        List<Tshirt> tshirtList = new ArrayList<>();
        tshirtList.add(tshirt);

        doReturn(tshirt).when(tshirtRepository).save(tshirt2);
        doReturn(Optional.of(tshirt)).when(tshirtRepository).findById(1);
        doReturn(tshirtList).when(tshirtRepository).findAll();

    }
    private void setUpConsoleRepositoryMock(){
        consoleRepository = mock(ConsoleRepository.class);
        Console console = new Console();
        console.setConsoleId(1);
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setProcessor("AMD");
        console.setMemoryAmount("32 GB");
        console.setPrice(new BigDecimal(1));
        console.setQuantity(1);

        Console console2 = new Console();
        console2.setModel("PS5");
        console2.setManufacturer("Sony");
        console2.setProcessor("AMD");
        console2.setMemoryAmount("32 GB");
        console2.setPrice(new BigDecimal(1));
        console2.setQuantity(1);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);

        doReturn(console).when(consoleRepository).save(console2);
        doReturn(Optional.of(console)).when(consoleRepository).findById(1);
        doReturn(consoleList).when(consoleRepository).findAll();

    }
    private void setUpGameRepositoryMock(){
        gameRepository = mock(GameRepository.class);
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Spider-Man");
        game.setEsrbRating("E");
        game.setDescription("Web swing");
        game.setStudio("Sony");
        game.setPrice(new BigDecimal(1));
        game.setQuantity(1);

        Game game2 = new Game();
        game2.setTitle("Spider-Man");
        game2.setEsrbRating("E");
        game2.setDescription("Web swing");
        game2.setStudio("Sony");
        game2.setPrice(new BigDecimal(1));
        game2.setQuantity(1);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        doReturn(game).when(gameRepository).save(game2);
        doReturn(Optional.of(game)).when(gameRepository).findById(1);
        doReturn(gameList).when(gameRepository).findAll();

    }
    private void setUpTaxRepositoryMock(){
        //taxRepository = mock(TaxRepository.class);
        //Tax tax = new Tax();
        //tax.setRate(new BigDecimal(.06));
        //tax.setState("CA");

    }
    private void setUpFeeRepositoryMock(){

    }

    @Test
    public void shouldSaveInvoice() {
        // Arrange
        InvoiceViewModel expectedResult = new InvoiceViewModel();
        expectedResult.setInvoiceId(1);
        expectedResult.setName("Frank");
        expectedResult.setStreet("Street");
        expectedResult.setCity("City");
        expectedResult.setState("CA");
        expectedResult.setZipcode("54321");
        expectedResult.setItemType("t-shirt");
        expectedResult.setItemId(1);
        expectedResult.setUnitPrice(new BigDecimal(10));
        expectedResult.setQuantity(10);
        expectedResult.setSubtotal(new BigDecimal(100));
        expectedResult.setTax(new BigDecimal(6));
        expectedResult.setProcessingFee(new BigDecimal(1.98));
        expectedResult.setTotal(new BigDecimal(107.98));


        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(1);
        ivm.setName("Frank");
        ivm.setStreet("Street");
        ivm.setCity("City");
        ivm.setState("CA");
        ivm.setZipcode("54321");
        ivm.setItemType("t-shirt");
        ivm.setItemId(1);
        ivm.setUnitPrice(new BigDecimal(10));
        ivm.setQuantity(10);
        ivm.setSubtotal(new BigDecimal(100));
        ivm.setTax(new BigDecimal(6));
        ivm.setProcessingFee(new BigDecimal(1.98));
        ivm.setTotal(new BigDecimal(107.98));

        // ACT
        ivm = service.saveInvoice(ivm);

        assertEquals(expectedResult, ivm);
    }

    @Test
    public void shouldFindInvoice() {
        // Arrange
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(1);
        ivm.setName("Frank");
        ivm.setStreet("Street");
        ivm.setCity("City");
        ivm.setState("CA");
        ivm.setZipcode("54321");
        ivm.setItemType("t-shirt");
        ivm.setItemId(1);
        ivm.setUnitPrice(new BigDecimal(10));
        ivm.setQuantity(10);
        ivm.setSubtotal(new BigDecimal(100));
        ivm.setTax(new BigDecimal(6));
        ivm.setProcessingFee(new BigDecimal(1.98));
        ivm.setTotal(new BigDecimal(107.98));

        // ACT
        InvoiceViewModel invoiceToFind = service.findInvoice(1);
        assertEquals(ivm, invoiceToFind);
    }

    @Test
    public void shouldFindAllInvoices() {
        // Arrange
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(1);
        ivm.setName("Frank");
        ivm.setStreet("Street");
        ivm.setCity("City");
        ivm.setState("CA");
        ivm.setZipcode("54321");
        ivm.setItemType("t-shirt");
        ivm.setItemId(1);
        ivm.setUnitPrice(new BigDecimal(10));
        ivm.setQuantity(10);
        ivm.setSubtotal(new BigDecimal(100));
        ivm.setTax(new BigDecimal(6));
        ivm.setProcessingFee(new BigDecimal(1.98));
        ivm.setTotal(new BigDecimal(107.98));

        // ACT
        List<InvoiceViewModel> invoiceToFind = service.findAllInvoices();
        assertEquals(ivm, invoiceToFind);



    }

    /*Invoice expectedResult = new Invoice();
        expectedResult.setInvoiceId(1);
        expectedResult.setName("Francisco");
        expectedResult.setStreet("Street");
        expectedResult.setCity("City");
        expectedResult.setState("CA");
        expectedResult.setZipcode("54321");
        expectedResult.setItemType("game");
        expectedResult.setItemId(1);
        expectedResult.setQuantity(1);

        Invoice invoice = new Invoice();
        invoice.setName("Francisco");
        invoice.setStreet("Street");
        invoice.setCity("City");
        invoice.setState("CA");
        invoice.setZipcode("54321");
        invoice.setItemType("game");
        invoice.setItemId(1);
        invoice.setQuantity(1);

     */


}