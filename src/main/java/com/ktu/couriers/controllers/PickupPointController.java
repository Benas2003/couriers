package com.ktu.couriers.controllers;

import com.ktu.couriers.models.PickupPoint;
import com.ktu.couriers.services.PickupPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pickup-point")
@Slf4j
public class PickupPointController {

    @Autowired
    PickupPointService pickupPointService;

    @GetMapping()
    public List<PickupPoint> getAllPickupPoints() {
        return this.pickupPointService.list();
    }

    @GetMapping("/{id}")
    public PickupPoint getPickupPoint(@PathVariable Long id) {
        return this.pickupPointService.get(id);
    }

    @PostMapping()
    public PickupPoint createPickupPoint(@RequestBody PickupPoint pickupPoint) {
        return this.pickupPointService.create(pickupPoint);
    }

    @PutMapping("/{id}")
    public PickupPoint updatePickupPoint(@PathVariable Long id, @RequestBody PickupPoint pickupPoint) {
        return this.pickupPointService.update(id, pickupPoint);
    }

    @DeleteMapping("/{id}")
    public void deletePickupPoint(@PathVariable Long id) {
        this.pickupPointService.delete(id);
    }
}
