package com.assignment.smartpark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkinglotResponse {

    private String location;

    private int capacity;

    private int occupiedSpaces;
}
