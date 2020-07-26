package ru.grishagin.greendatatask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grishagin.greendatatask.model.Bank;
import ru.grishagin.greendatatask.service.DescriptorService;
import ru.grishagin.greendatatask.service.ProcessingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    
    private final ProcessingService<Bank> bankService;

    private final DescriptorService descriptorService;

    @Autowired
    public BankController(ProcessingService<Bank> bankService, DescriptorService descriptorService) {
        this.bankService = bankService;
        this.descriptorService = descriptorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> get(@PathVariable Long id){
        return ResponseEntity.of(bankService.getById(id));
    }

    @GetMapping
    public List<Bank> getByDescriptor(HttpServletRequest request){
        return bankService.getByDescriptor(descriptorService.buildDescriptor(request));
    }

    @PostMapping
    public Bank create(@RequestBody Bank bank){
        return bankService.create(bank);
    }

    @PatchMapping
    public Bank update(@RequestBody Bank bank){
        return bankService.update(bank);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        bankService.delete(id);
    }
}
