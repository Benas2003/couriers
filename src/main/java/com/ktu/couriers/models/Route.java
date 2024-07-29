package com.ktu.couriers.models;

import com.ktu.couriers.enums.PickupPointStatus;
import com.ktu.couriers.enums.RouteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courierId;

    @Enumerated(EnumType.STRING)
    private RouteStatus status;

    @Transient
    private List<RouteWaypoint> waypoints;

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", courierId=" + courierId +
                ", status=" + status +
                ", waypoints=" + waypoints +
                '}';
    }
}
