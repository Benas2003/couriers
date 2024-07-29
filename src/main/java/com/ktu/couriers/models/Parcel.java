package com.ktu.couriers.models;

import com.ktu.couriers.enums.ParcelStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pickupAddress;
    private Long pickupPointId;

    private String deliveryAddress;
    private Long deliveryPointId;

    private Long clientId;
    private Long routeId;

    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverEmail;

    private float weight;
    private float x;
    private float y;
    private float z;

    private String trackingNumber;
    @Enumerated(EnumType.STRING)
    private ParcelStatus status;

    @Enumerated(EnumType.STRING)
    private DeliveryMode deliveryMode;

    public Parcel(String pickupAddress, Long pickupPointId, String deliveryAddress, Long deliveryPointId, Long clientId, Long routeId, String trackingNumber, ParcelStatus status, DeliveryMode deliveryMode, String receiverName, String receiverPhoneNumber, String receiverEmail) {
        this.pickupAddress = pickupAddress;
        this.pickupPointId = pickupPointId;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPointId = deliveryPointId;
        this.clientId = clientId;
        this.routeId = routeId;
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.deliveryMode = deliveryMode;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.receiverEmail = receiverEmail;
    }

    // toString method
    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", pickupPointId=" + pickupPointId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryPointId=" + deliveryPointId +
                ", clientId=" + clientId +
                ", routeId=" + routeId +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", status=" + status +
                ", deliveryMode=" + deliveryMode +
                '}';
    }



    public enum DeliveryMode {
        CLIENT_TO_CLIENT,
        CLIENT_TO_PICKUP,
        PICKUP_TO_CLIENT,
        PICKUP_TO_PICKUP,
        STORAGING,
    }
}
