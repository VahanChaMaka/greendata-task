package ru.grishagin.greendatatask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grishagin.greendatatask.model.Deposit;
import ru.grishagin.greendatatask.service.DescriptorService;
import ru.grishagin.greendatatask.service.ProcessingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final ProcessingService<Deposit> depositService;

    private final DescriptorService descriptorService;

    @Autowired
    public DepositController(ProcessingService<Deposit> depositService, DescriptorService descriptorService) {
        this.depositService = depositService;
        this.descriptorService = descriptorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deposit> get(@PathVariable Long id){
        return ResponseEntity.of(depositService.getById(id));
    }

    @GetMapping
    public List<Deposit> getByDescriptor(HttpServletRequest request){
        return depositService.getByDescriptor(descriptorService.buildDescriptor(request));
    }

    @PostMapping
    public Deposit create(@RequestBody Deposit deposit){
        return depositService.create(deposit);
    }

    @PatchMapping
    public Deposit update(@RequestBody Deposit deposit){
        return depositService.update(deposit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        depositService.delete(id);
    }
}
