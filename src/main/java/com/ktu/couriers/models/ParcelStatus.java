package com.ktu.couriers.models;

public enum ParcelStatus {
    CREATED,
    WAITING_FOR_PICKUP,
    PICKED_UP,
    IN_TRANSIT,
    SORTED,
    IN_DELIVERY,
    DELIVERED,
    // Only if the parcel is created
    CANCELLED,
    // Only if the parcel is not picked up
    RETURNED,
    LOST,
    UNKNOWN,
}
