package com.assignment.smartpark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {

    private String licensePlate;

    private String type;

    private String ownerName;

}
