package com.ktu.couriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSearch {
    String name;
    String surname;
    String email;
    String phone;
    String address;
}

