package com.ktu.couriers.controllers;

import com.ktu.couriers.models.Client;
import com.ktu.couriers.models.ClientSearch;
import com.ktu.couriers.services.ClientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clients")
@Slf4j
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @GetMapping()
    public List<Client> getAllClients() {
        log.info("Getting all clients");
        return this.clientsService.list();
    }

    @GetMapping("/search")
    public List<Client> getClientsBySearch(@ModelAttribute ClientSearch clientSearch) {
        log.info("Searching clients by: {}", clientSearch);
        return this.clientsService.search(clientSearch);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        log.info("Getting client by id: {}", id);
        return this.clientsService.get(id);
    }

    @PostMapping()
    public Client createClient(@RequestBody(required = true) Client client) {
        log.info("Creating client: {}", client);
        return this.clientsService.create(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@RequestBody(required = true) Client client) {
        log.info("Updating client: {}", client);
        return this.clientsService.update(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        log.info("Deleting client by id: {}", id);
        this.clientsService.delete(id);
    }
}
