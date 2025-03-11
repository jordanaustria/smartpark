package com.assignment.smartpark.dto;

public class ParkinglotResponse {

    private String location;

    private int capacity;

    private int occupiedSpaces;

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedSpaces() {
        return occupiedSpaces;
    }

    public ParkinglotResponse(String location, int capacity, int occupiedSpaces) {
        this.location = location;
        this.capacity = capacity;
        this.occupiedSpaces = occupiedSpaces;
    }

    public ParkinglotResponse() {
    }
}
