package com.ktu.couriers.controllers;

import com.ktu.couriers.enums.PickupPointStatus;
import com.ktu.couriers.models.PickupPoint;
import com.ktu.couriers.utils.JwtUtil;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Iterator;
import java.util.List;

public class MockPickupPointController extends MockCRUDController<PickupPoint> {

    private final String path = "/pickup-point";

    public final String addition = "(JUNIT)";

    public MockPickupPointController(MockMvc mockMvc, JwtUtil jwtUtil) {
        super(PickupPoint.class, mockMvc, jwtUtil);
    }

    public PickupPoint createPickupPoint() {
        return this.generatePickupPoint();
    }


    private PickupPoint generatePickupPoint() {
        return PickupPoint.builder()
                .name(addition + " John Doe")
                .address("City, Street 1-1")
                .status(PickupPointStatus.ACTIVE)
                .build();
    }

    public PickupPoint create(PickupPoint pickupPoint) throws Exception {
        return super.post(path, pickupPoint);
    }

    public PickupPoint createAndGet() throws Exception {
        PickupPoint response = this.create(this.createPickupPoint());
        return this.get(response);
    }

    public PickupPoint get(PickupPoint pickupPoint) throws Exception {
        String requestUrl = createUrl(path, pickupPoint);
        return super.get(requestUrl);
    }

    public PickupPoint update(PickupPoint pickupPoint) throws Exception {
        String requestUrl = createUrl(path, pickupPoint);
        return super.put(requestUrl, pickupPoint);
    }

    public void delete(PickupPoint pickupPoint) throws Exception {
        String requestUrl = createUrl(path, pickupPoint);
        super.delete(requestUrl);

    }

    public List<PickupPoint> list() throws Exception {
        return super.list(path);
    }

    public String createUrl(String path, PickupPoint pickupPoint) {
        StringBuilder url = new StringBuilder(path);
        url.append("/").append(pickupPoint.getId());
        return url.toString();
    }

    public void cleanUp() throws Exception {
        Iterator<PickupPoint> iterator = this.list().iterator();
        while (iterator.hasNext()) {
            PickupPoint pickupPoint = iterator.next();
            if(pickupPoint.getName().contains(addition)) {
                try {
                    this.delete(pickupPoint);
                } catch (Exception e) {
                    System.err.println("Failed to delete client: " + pickupPoint.getId());
                }
            }
        }
    }

}