package com.ktu.couriers.services;

import com.ktu.couriers.models.Parcel;
import com.ktu.couriers.repositories.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;

    public List<Parcel> list() {
        return this.parcelRepository.findAll();
    }

    public Parcel get(Long id) {
        return this.parcelRepository.findById(id).orElse(null);
    }

    public Parcel create(Parcel parcel) {
        String uniqueTrackingNumber = generateUniqueTrackingNumber();
        parcel.setTrackingNumber(uniqueTrackingNumber);
        return this.parcelRepository.save(parcel);
    }

    public Parcel update(Long id, Parcel parcel) {
        return this.parcelRepository.save(parcel);
    }

    public void delete(Long id) {
        this.parcelRepository.deleteById(id);
    }

    private String generateUniqueTrackingNumber() {
        // Implement your unique tracking number generation logic here
        // For example, you could use a UUID
        return UUID.randomUUID().toString();
    }
}
