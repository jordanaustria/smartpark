package com.assignment.smartpark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    private String lisencePlate;

    private String type;

    private String ownerName;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parkingLot;

    public Vehicle(String lisencePlate, String type, String ownerName) {
        this.lisencePlate = lisencePlate;
        this.type = type;
        this.ownerName = ownerName;
    }
}
