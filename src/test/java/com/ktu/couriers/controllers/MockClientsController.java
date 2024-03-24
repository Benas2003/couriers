package com.ktu.couriers.controllers;

import com.ktu.couriers.models.Client;
import com.ktu.couriers.models.ClientSearch;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Iterator;
import java.util.List;

public class MockClientsController extends MockCRUDController<Client> {

    private final String path = "/clients";

    public final String addition = "(JUNIT)";

    public MockClientsController(MockMvc mockMvc) {
        super(Client.class, mockMvc);
    }

    public Client createSimpleClient() {
        return this.generateSimpleClient();
    }

    public Client createBusinessClient() {
        return this.generateBusinessClient();
    }

    private Client generateSimpleClient() {
        return Client.builder()
                .name(addition + " John Doe")
                .email("test@test.com")
                .address("City, Street 1-1")
                .phoneNumber("123456789")
                .build();
    }

    private Client generateBusinessClient() {
        return Client.builder()
                .name(addition + " Business Ltd.")
                .company("56789511")
                .email("business@test.com")
                .address("City, Street 1-1")
                .phoneNumber("123456789")
                .build();
    }

    public Client create(Client client) throws Exception {
        return super.post(path, client);
    }

    public Client createAndGet() throws Exception {
        Client response = this.create(this.createSimpleClient());
        return this.get(response);
    }

    public Client get(Client client) throws Exception {
        String requestUrl = createUrl(path, client);
        return super.get(requestUrl);
    }

    public Client update(Client client) throws Exception {
        String requestUrl = createUrl(path, client);
        return super.put(requestUrl, client);
    }

    public void delete(Client client) throws Exception {
        String requestUrl = createUrl(path, client);
        super.delete(requestUrl);

    }

    public List<Client> search(ClientSearch search) throws Exception {
        String url = path + "/search?" + search.toURL();
        return super.search(url);
    }

    public List<Client> list() throws Exception {
        return super.list(path);
    }

    public String createUrl(String path, Client client) {
        StringBuilder url = new StringBuilder(path);
        url.append("/").append(client.getId());
        return url.toString();
    }

    public void cleanUp() throws Exception {
        Iterator<Client> iterator = this.list().iterator();
        while (iterator.hasNext()) {
            Client client = iterator.next();
            if(client.getName().contains(addition)) {
                try {
                    this.delete(client);
                } catch (Exception e) {
                    System.err.println("Failed to delete client: " + client.getId());
                }
            }
        }
    }

}