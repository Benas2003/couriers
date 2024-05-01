package com.ktu.couriers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String companyName;
    private String email;

    private String password;

    private String phoneNumber;
    private Date createdAt;
    private Date lastLoginAt;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String address;
    @Enumerated(EnumType.STRING)
    private ClientType type;
    private String companyRegistrationCode;
    private String companyVAT;

    @Transient
    private String token;

    // constructor
    public User(String firstName, String lastName, String companyName, String email, String password, String phoneNumber, Date createdAt, Date lastLoginAt, UserRole role, String address, ClientType type, String companyRegistrationCode, String companyVAT) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
        this.role = role;
        this.address = address;
        this.type = type;
        this.companyRegistrationCode = companyRegistrationCode;
        this.companyVAT = companyVAT;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                ", lastLoginAt=" + lastLoginAt +
                ", role=" + role +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", companyRegistrationCode='" + companyRegistrationCode + '\'' +
                ", companyVAT='" + companyVAT + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
