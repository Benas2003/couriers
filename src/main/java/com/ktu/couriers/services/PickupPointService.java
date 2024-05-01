package com.ktu.couriers.services;

import com.ktu.couriers.models.PickupPoint;
import com.ktu.couriers.repositories.PickupPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickupPointService {

    @Autowired
    private PickupPointRepository pickupPointRepository;

    public List<PickupPoint> list() {
        return this.pickupPointRepository.findAll();
    }

    public PickupPoint get(Long id) {
        return this.pickupPointRepository.findById(id).orElse(null);
    }

    public PickupPoint create(PickupPoint pickupPoint) {
        return this.pickupPointRepository.save(pickupPoint);
    }

    public PickupPoint update(Long id, PickupPoint pickupPoint) {
        return this.pickupPointRepository.save(pickupPoint);
    }

    public void delete(Long id) {
        this.pickupPointRepository.deleteById(id);
    }
}
