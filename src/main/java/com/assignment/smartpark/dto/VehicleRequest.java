package com.assignment.smartpark.dto;

public class VehicleRequest {

    private String licensePlate;

    private String type;

    private String ownerName;

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public VehicleRequest(String licensePlate, String type, String ownerName) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.ownerName = ownerName;
    }
}
