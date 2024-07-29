package com.ktu.couriers.controllers;

import com.ktu.couriers.enums.PickupPointStatus;
import com.ktu.couriers.models.PickupPoint;
import com.ktu.couriers.utils.JwtUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class PickUpPointTests {
    private static MockPickupPointController api;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        api = new MockPickupPointController(mockMvc, jwtUtil);
    }

    @AfterAll
    static void tearDown() throws Exception {
        api.cleanUp();
    }

    @Test
    @DisplayName("Test creating a pickup point")
    void createPickupPoint() throws Exception {
        PickupPoint pickupPoint = api.createPickupPoint();
        PickupPoint response = api.create(pickupPoint);
        pickupPoint.setId(response.getId());

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(pickupPoint);

    }

    @Test
    @DisplayName("Test get pickup point")
    void getClient() throws Exception {
        PickupPoint client = api.createAndGet();
        PickupPoint response = api.get(client);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(client);
    }

    @Test
    @DisplayName("Test update pickup point")
    void updateClient() throws Exception {
        PickupPoint pickupPoint = api.createAndGet();
        pickupPoint.setStatus(PickupPointStatus.INACTIVE);
        PickupPoint response = api.update(pickupPoint);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(pickupPoint);
    }

    @Test
    @DisplayName("Test delete pickup point")
    void deleteClient() throws Exception {
        PickupPoint client = api.createAndGet();
        api.delete(client);
    }

    @Test
    @DisplayName("Test list pickup points")
    void listClients() throws Exception {
        List<PickupPoint> existingList = api.list();

        PickupPoint pickupPoint = api.createPickupPoint();
        api.create(pickupPoint);
        pickupPoint = api.createPickupPoint();
        pickupPoint.setName("(JUNIT) Test pickup point 2");
        api.create(pickupPoint);

        List<PickupPoint> response = api.list();

        assertThat(response)
                .hasSize(existingList.size() + 2);
    }
}
