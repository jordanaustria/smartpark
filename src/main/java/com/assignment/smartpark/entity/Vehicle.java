package com.assignment.smartpark.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    private String licensePlate;

    private String type;

    private String ownerName;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    public Vehicle(String licensePlate, String type, String ownerName) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.ownerName = ownerName;
    }

    public Vehicle() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
