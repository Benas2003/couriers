package com.ktu.couriers.services;

import com.ktu.couriers.models.Client;
import com.ktu.couriers.models.ClientSearch;
import com.ktu.couriers.repositories.ClientsRepository;
import com.ktu.couriers.utils.ClientsSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientsService {

    @Autowired
    private ClientsRepository clientsRepository;

    public List<Client> list() {
        return this.clientsRepository.findAll();
    }

    public Client get(Long id) {
        return this.clientsRepository.findById(id).orElse(null);
    }

    public List<Client> search(ClientSearch search) {
        search.decodeFields();
        Specification<Client> spec = ClientsSpecifications.withCriteria(search);
        return this.clientsRepository.findAll(spec);
    }

    public Client create(Client client) {
        return this.clientsRepository.save(client);
    }

    public Client update(Client client) {
        return this.clientsRepository.save(client);
    }

    public void delete(Long id) {
        this.clientsRepository.deleteById(id);
    }
}
