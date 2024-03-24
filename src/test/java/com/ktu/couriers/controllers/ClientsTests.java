package com.ktu.couriers.controllers;

import com.ktu.couriers.models.Client;
import com.ktu.couriers.models.ClientSearch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientsTests {
    private static MockClientsController api;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        api = new MockClientsController(mockMvc);
    }

    @AfterAll
    static void tearDown() throws Exception {
        api.cleanUp();
    }

    @Test
    @DisplayName("Test creating a simple client")
    void createSimpleClient() throws Exception {
        Client client = api.createSimpleClient();
        Client response = api.create(client);
        client.setId(response.getId());

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(client);

    }

    @Test
    @DisplayName("Test creating a business client")
    void createBusinessClient() throws Exception {
        Client client = api.createBusinessClient();
        Client response = api.create(client);
        client.setId(response.getId());

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(client);
    }

    @Test
    @DisplayName("Test get client")
    void getClient() throws Exception {
        Client client = api.createAndGet();
        Client response = api.get(client);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(client);
    }

    @Test
    @DisplayName("Test update client")
    void updateClient() throws Exception {
        Client client = api.createAndGet();
        client.setName(api.addition + " Updated Name");
        Client response = api.update(client);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(client);
    }

    @Test
    @DisplayName("Test delete client")
    void deleteClient() throws Exception {
        Client client = api.createAndGet();
        api.delete(client);
    }

    @Test
    @DisplayName("Test search clients")
    void searchClients() throws Exception {
        Client client = api.createSimpleClient();
        api.create(client);
        client = api.createBusinessClient();
        api.create(client);

        ClientSearch search = new ClientSearch();
        search.setAddress("City, Street 1-1");
        List<Client> response = api.search(search);

        assertThat(response)
                .hasSize(2);
    }

    @Test
    @DisplayName("Test list clients")
    void listClients() throws Exception {
        List<Client> existingList = api.list();

        Client client = api.createSimpleClient();
        api.create(client);
        client = api.createBusinessClient();
        api.create(client);

        List<Client> response = api.list();

        assertThat(response)
                .hasSize(existingList.size() + 2);
    }
}
