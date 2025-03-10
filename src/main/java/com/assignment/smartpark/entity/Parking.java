package com.assignment.smartpark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "parking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, length = 50, nullable = false)
    private String location;

    private int capacity;

    private int occupied = 0;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL)
    private List<Vehicle> parkedVehicles;

    public Parking(String location, int capacity, int occupiedSpaces) {
        this.location = location;
        this.capacity = capacity;
        this.occupied = occupiedSpaces;
    }

    public Parking(UUID id, String location, int capacity, int occupied) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.occupied = occupied;
    }

    public boolean isFull() {
        return occupied >= capacity;
    }

    public void parkVehicle() {
        if (!isFull()) occupied++;
    }

    public void removeVehicle() {
        if (occupied > 0) occupied--;
    }
}
