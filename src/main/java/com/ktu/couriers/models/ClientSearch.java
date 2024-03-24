package com.ktu.couriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSearch {
    private String name;
    private String company;
    private String address;
    private String phoneNumber;
    private String email;

    public String toURL() {
        StringJoiner queryString = new StringJoiner("&");

        addParam(queryString, "name", name);
        addParam(queryString, "company", company);
        addParam(queryString, "address", address);
        addParam(queryString, "phoneNumber", phoneNumber);
        addParam(queryString, "email", email);

        return queryString.toString();
    }

    private void addParam(StringJoiner joiner, String key, String value) {
        if (value != null && !value.isEmpty()) {
            String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8);
            joiner.add(key + "=" + encodedValue);
        }
    }

    public void decodeFields() {
        this.name = decodeValue(this.name);
        this.company = decodeValue(this.company);
        this.address = decodeValue(this.address);
        this.phoneNumber = decodeValue(this.phoneNumber);
        this.email = decodeValue(this.email);
    }

    private String decodeValue(String value) {
        if (value != null && !value.isEmpty()) {
            return URLDecoder.decode(value, StandardCharsets.UTF_8);
        }
        return value;
    }
}

