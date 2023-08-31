package com.company.gamestore.service;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ConsoleServiceLayer {
    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleServiceLayer(ConsoleRepository consoleRepository){
        this.consoleRepository = consoleRepository;
    }

    public ConsoleViewModel buildConsoleViewModel(Console console){
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setConsoleId(console.getConsoleId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setQuantity(console.getQuantity());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setMemoryAmount(console.getMemoryAmount());

        return consoleViewModel;



    }
    @org.springframework.transaction.annotation.Transactional
    public ConsoleViewModel saveConsole(ConsoleViewModel consoleViewModel){
        Console newConsole= new Console();
        newConsole.setManufacturer(consoleViewModel.getManufacturer());
        newConsole.setConsoleId(consoleViewModel.getConsoleId());
        newConsole.setModel(consoleViewModel.getModel());
        newConsole.setProcessor(consoleViewModel.getProcessor());
        newConsole.setQuantity(consoleViewModel.getQuantity());
        newConsole.setPrice(consoleViewModel.getPrice());

        ConsoleViewModel icm = buildConsoleViewModel(newConsole);

        consoleViewModel.setProcessor(icm.getProcessor());
        ///consoleViewModel.setConsoleId(icm.getConsoleId());
        consoleViewModel.setManufacturer((icm.getManufacturer()));
        consoleViewModel.setMemoryAmount(icm.getMemoryAmount());
        consoleViewModel.setPrice(icm.getPrice());
        consoleViewModel.setModel(icm.getModel());
        consoleViewModel.setQuantity(icm.getQuantity());

        newConsole.setProcessor(icm.getProcessor());
        ///consoleViewModel.setConsoleId(icm.getConsoleId());
        newConsole.setManufacturer((icm.getManufacturer()));
        newConsole.setMemoryAmount(icm.getMemoryAmount());
        newConsole.setPrice(icm.getPrice());
        newConsole.setModel(icm.getModel());
        newConsole.setQuantity(icm.getQuantity());


        newConsole = consoleRepository.save(newConsole);
        consoleViewModel.setConsoleId(newConsole.getConsoleId());
        return consoleViewModel;
    }
    @org.springframework.transaction.annotation.Transactional
    public ConsoleViewModel updateConsole(ConsoleViewModel consoleViewModel){
        Console console = new Console();
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setModel(consoleViewModel.getModel());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setQuantity(consoleViewModel.getQuantity());
        console.setPrice(consoleViewModel.getPrice());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setConsoleId(consoleViewModel.getConsoleId());

        try {
            console = consoleRepository.save(console);
            return buildConsoleViewModel(console);
        } catch (IllegalArgumentException e){
            return null;
        }

    }
    @org.springframework.transaction.annotation.Transactional
    public void deleteConsole(int id){
        consoleRepository.deleteById(id);
    }

    public ConsoleViewModel findConsoleById(int id){
        Optional<Console> console = consoleRepository.findById(id);
        return console.isPresent() ? buildConsoleViewModel(console.get()) : null;
    }

    public List<ConsoleViewModel> findAllConsoles(){
        List<Console> consoleList = consoleRepository.findAll();
        List<ConsoleViewModel> viewModelList = new ArrayList<>();
        for (Console console : consoleList){
            ConsoleViewModel consoleViewModel = buildConsoleViewModel(console);
            viewModelList.add(consoleViewModel);
        }
        return viewModelList;
    }
    public List<ConsoleViewModel> findConsolesByManufacturer(String manufacturer){
        List<Console> consoleList = consoleRepository.findByManufacturer(manufacturer);
        List<ConsoleViewModel> viewModelList = new ArrayList<>();
        for (Console console : consoleList){
            ConsoleViewModel consoleViewModel = buildConsoleViewModel(console);
            viewModelList.add(consoleViewModel);
        }
        return viewModelList;
    }
}
