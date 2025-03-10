package com.assignment.smartpark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkinglotRequest {

    private String location;

    private int capacity;

    private int occupiedSpaces;
}
