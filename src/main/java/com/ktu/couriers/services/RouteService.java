package com.ktu.couriers.services;

import com.ktu.couriers.enums.ParcelStatus;
import com.ktu.couriers.models.Parcel;
import com.ktu.couriers.models.Route;
import com.ktu.couriers.repositories.ParcelRepository;
import com.ktu.couriers.repositories.RouteRepository;
import com.ktu.couriers.repositories.RouteWaypointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    RouteRepository routeRepo;

    @Autowired
    RouteWaypointRepository routeWaypointRepo;

    @Autowired
    ParcelService parcelService;

    public List<Route> list() {
        List<Route> routes = this.routeRepo.findAll();
        routes.forEach(route -> route.setWaypoints(this.routeWaypointRepo.findAllByRouteId(route.getId())));
        return routes;
    }

    public Route get(Long id) {
        Route route = this.routeRepo.findById(id).orElse(null);
        if (route != null) {
            route.setWaypoints(this.routeWaypointRepo.findAllByRouteId(route.getId()));
        }
        return route;
    }

    public Route create(Route route) {
        Route savedRoute = this.routeRepo.save(route);
        route.getWaypoints().forEach(waypoint -> waypoint.setRouteId(savedRoute.getId()));

        route.getWaypoints().forEach(waypoint -> {
            Parcel parcel = this.parcelService.get(waypoint.getParcelId());
            parcel.setRouteId(savedRoute.getId());
            parcel.setStatus(ParcelStatus.WAITING_FOR_PICKUP);
            this.parcelService.update(parcel.getId(), parcel);
        });

        this.routeWaypointRepo.saveAll(route.getWaypoints());
        return savedRoute;
    }

    public Route update(Long id, Route route) {
        Route savedRoute = this.routeRepo.save(route);
        route.getWaypoints().forEach(waypoint -> waypoint.setRouteId(savedRoute.getId()));
        this.routeWaypointRepo.saveAll(route.getWaypoints());
        return savedRoute;
    }

    public void delete(Long id) {
        Route route = this.get(id);
        route.getWaypoints().forEach(waypoint -> {
            Parcel parcel = this.parcelService.get(waypoint.getParcelId());
            parcel.setRouteId(null);
            parcel.setStatus(ParcelStatus.CREATED);
            this.parcelService.update(parcel.getId(), parcel);
            this.routeWaypointRepo.deleteById(waypoint.getId());
        });
        this.routeRepo.deleteById(id);
    }
}
