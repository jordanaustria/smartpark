package com.assignment.smartpark.dto;

public class ParkinglotRequest {

    private String location;

    private int capacity;

    private int occupiedSpaces;

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupiedSpaces() {
        return occupiedSpaces;
    }

    public ParkinglotRequest(String location, int capacity, int occupiedSpaces) {
        this.location = location;
        this.capacity = capacity;
        this.occupiedSpaces = occupiedSpaces;
    }

    public void setOccupiedSpaces(int occupiedSpaces) {
        this.occupiedSpaces = occupiedSpaces;
    }
}
