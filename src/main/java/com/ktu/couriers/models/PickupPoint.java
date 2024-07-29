package com.ktu.couriers.models;

import com.ktu.couriers.enums.PickupPointStatus;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PickupPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private PickupPointStatus status;

    public PickupPoint(String name, String address, PickupPointStatus status) {
        this.setName(name);
        this.setAddress(address);
        this.setStatus(status);
    }

    @Override
    public String toString() {
        return "PickupPoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }

}
