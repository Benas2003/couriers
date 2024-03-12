package com.ktu.couriers.controllers;

import com.ktu.couriers.models.Route;
import com.ktu.couriers.models.RouteSearch;
import com.ktu.couriers.services.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/route")
@Slf4j
public class RouteController {

    @Autowired
    RouteService routeService;

    @GetMapping()
    public List<Route> getAllRoutes() {
        return this.routeService.list();
    }

    @GetMapping("/search")
    public List<Route> getRoutesBySearch(@PathVariable RouteSearch routeSearch) {
        return this.routeService.search(routeSearch);
    }

    @GetMapping("/{id}")
    public Route getRoute(@PathVariable Long id) {
        return this.routeService.get(id);
    }

    @PostMapping()
    public Route createRoute(@RequestBody(required = true) Route route) {
        return this.routeService.create(route);
    }

    @PutMapping("/{id}")
    public Route updateRoute(@PathVariable Long id, @RequestBody(required = true) Route route) {
        return this.routeService.update(id, route);
    }

    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Long id) {
        this.routeService.delete(id);
    }
}
