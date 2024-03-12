package com.ktu.couriers.controllers;

import com.ktu.couriers.models.Client;
import com.ktu.couriers.models.ClientSearch;
import com.ktu.couriers.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/client")
@Slf4j
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping()
    public List<Client> getAllClients() {
        return this.clientService.list();
    }

    @GetMapping("/search")
    public List<Client> getClientsBySearch(@PathVariable ClientSearch clientSearch) {
        return this.clientService.search(clientSearch);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return this.clientService.get(id);
    }

    @PostMapping()
    public Client createClient(@RequestBody(required = true) Client client) {
        return this.clientService.create(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody(required = true) Client client) {
        return this.clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        this.clientService.delete(id);
    }
}
