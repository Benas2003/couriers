package com.ktu.couriers.controllers;

import com.ktu.couriers.models.Parcel;
import com.ktu.couriers.services.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/parcel")
@Slf4j
public class ParcelController {

    @Autowired
    ParcelService parcelService;

    @GetMapping()
    public List<Parcel> getAllParcels() {
        return this.parcelService.list();
    }


    @GetMapping("/{id}")
    public Parcel getParcel(@PathVariable Long id) {
        return this.parcelService.get(id);
    }

    @PostMapping()
    public Parcel createParcel(@RequestBody(required = true) Parcel parcel) {
        return this.parcelService.create(parcel);
    }

    @PutMapping("/{id}")
    public Parcel updateParcel(@PathVariable Long id, @RequestBody(required = true) Parcel parcel) {
        return this.parcelService.update(id, parcel);
    }

    @DeleteMapping("/{id}")
    public void deleteParcel(@PathVariable Long id) {
        this.parcelService.delete(id);
    }
}
