package ru.grishagin.greendatatask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grishagin.greendatatask.model.Client;
import ru.grishagin.greendatatask.repository.ClientRepository;
import ru.grishagin.greendatatask.service.DescriptorService;
import ru.grishagin.greendatatask.service.ProcessingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ProcessingService<Client> clientService;

    private final DescriptorService descriptorService;

    @Autowired
    public ClientController(ProcessingService<Client> clientService, DescriptorService descriptorService) {
        this.clientService = clientService;
        this.descriptorService = descriptorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable Long id){
        return ResponseEntity.of(clientService.getById(id));
    }

    @GetMapping
    public List<Client> getByDescriptor(HttpServletRequest request){
        return clientService.getByDescriptor(descriptorService.buildDescriptor(request));
    }

    @PostMapping
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @PatchMapping
    public Client update(@RequestBody Client client){ //TODO: absent fields?
        return clientService.create(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clientService.delete(id);
    }

}
