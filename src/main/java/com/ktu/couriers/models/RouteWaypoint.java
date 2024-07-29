package com.ktu.couriers.models;

import com.ktu.couriers.enums.RouteWaypointStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteWaypoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long routeId;
    private Long parcelId;

    @Enumerated(EnumType.STRING)
    private RouteWaypointStatus status;

    private String pickupAddress;
    @Transient
    private String deliveryAddress;

    // to string
    @Override
    public String toString() {
        return "RouteWaypoint{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", parcelId=" + parcelId +
                ", status=" + status +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
