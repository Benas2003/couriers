package com.ktu.couriers.repositories;

import com.ktu.couriers.models.RouteWaypoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteWaypointRepository extends JpaRepository<RouteWaypoint, Long>, JpaSpecificationExecutor<RouteWaypoint> {

    @Query("SELECT rw FROM RouteWaypoint rw WHERE rw.routeId = :routeId")
    List<RouteWaypoint> findAllByRouteId(@Param("routeId") Long routeId);
}
