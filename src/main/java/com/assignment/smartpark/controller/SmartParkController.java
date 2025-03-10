package com.assignment.smartpark.controller;

import com.assignment.smartpark.dto.ParkinglotRequest;
import com.assignment.smartpark.dto.ParkinglotResponse;
import com.assignment.smartpark.dto.VehicleRequest;
import com.assignment.smartpark.dto.VehicleResponse;
import com.assignment.smartpark.entity.Parking;
import com.assignment.smartpark.service.SmartParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("smartpark")
public class SmartParkController {

    @Autowired
    SmartParkService service;

    @PostMapping("/register/parkinglot")
    public ResponseEntity<ParkinglotResponse> registerParkinglot(@RequestBody ParkinglotRequest request) {
        ParkinglotResponse response = service.registerParkinglot(request);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/register/vehicle")
    public ResponseEntity<VehicleResponse> registerVehicle(@RequestBody VehicleRequest vehicle) {
        VehicleResponse response = service.registerVehicle(vehicle);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/checkin/vehicle")
    public String checkInVehicle(@RequestParam String licensePlate, @RequestParam String parkingLocation) {
        return service.checkInVehicle(licensePlate, UUID.fromString(parkingLocation));
    }

    @PutMapping("/checkout/vehicle")
    public String checkOutVehicle(@RequestParam String licensePlate) {
        return service.checkOutVehicle(licensePlate);
    }

    @GetMapping("/all")
    public List<Parking> getAllParkingLots() {
        return service.getAllParkingLots();
    }

    @GetMapping("/available")
    public int getAvailableSpaces(@RequestParam String location) {
        return service.getAvailableSpaces(location);
    }

}
