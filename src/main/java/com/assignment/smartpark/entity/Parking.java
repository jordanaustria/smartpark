package com.assignment.smartpark.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, length = 50, nullable = false)
    private String location;

    private int capacity;

    private int occupied = 0;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vehicle> parkedVehicles;

    public Parking(String location, int capacity, int occupiedSpaces) {
        this.location = location;
        this.capacity = capacity;
        this.occupied = occupiedSpaces;
    }

    public Parking() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Vehicle> getParkedVehicles() {
        return parkedVehicles;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
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
