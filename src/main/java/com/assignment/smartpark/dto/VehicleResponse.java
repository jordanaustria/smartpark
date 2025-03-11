package com.assignment.smartpark.dto;

public class VehicleResponse {
    private String licensePlate;

    private String type;

    private String ownerName;

    public VehicleResponse(String licensePlate, String type, String ownerName) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.ownerName = ownerName;
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

    public VehicleResponse() {
    }
}
