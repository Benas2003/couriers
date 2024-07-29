package com.ktu.couriers.services;

import com.ktu.couriers.models.Parcel;
import com.ktu.couriers.models.RouteWaypoint;
import com.ktu.couriers.repositories.ParcelRepository;
import com.ktu.couriers.repositories.RouteWaypointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteWaypointService {

    @Autowired
    RouteWaypointRepository routeWaypointRepo;

    @Autowired
    ParcelRepository parcelRepo;

    public RouteWaypoint get(Long id) {
        RouteWaypoint waypoint = this.routeWaypointRepo.findById(id).orElse(null);
        if (waypoint != null) {
            Parcel parcel = this.parcelRepo.findById(waypoint.getParcelId()).orElse(null);
            if (parcel != null) {
                waypoint.setPickupAddress(parcel.getPickupAddress());
                waypoint.setDeliveryAddress(parcel.getDeliveryAddress());
            }
        }
        return waypoint;
    }
}
