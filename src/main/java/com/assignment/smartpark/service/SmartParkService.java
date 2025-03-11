package com.assignment.smartpark.service;

import com.assignment.smartpark.dto.ParkinglotRequest;
import com.assignment.smartpark.dto.ParkinglotResponse;
import com.assignment.smartpark.dto.VehicleRequest;
import com.assignment.smartpark.dto.VehicleResponse;
import com.assignment.smartpark.entity.Parking;
import com.assignment.smartpark.entity.Vehicle;
import com.assignment.smartpark.repository.ParkinglotRepository;
import com.assignment.smartpark.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SmartParkService {

    @Autowired
    ParkinglotRepository parkinglotRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Transactional
    public ParkinglotResponse registerParkinglot(ParkinglotRequest request) {
        Optional<Parking> parkingLocationCheck = parkinglotRepository.findByLocation(request.getLocation());

        if (parkingLocationCheck.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parking location already exists!");
        }

        Parking parking = new Parking(request.getLocation(), request.getCapacity(), request.getOccupiedSpaces());

        Parking savedParking = parkinglotRepository.save(parking);
        return new ParkinglotResponse(
                savedParking.getLocation(),
                savedParking.getCapacity(),
                savedParking.getOccupied()
        );
    }

    @Transactional
    public VehicleResponse registerVehicle(VehicleRequest request) {
        Optional<Vehicle> licenseCheck = vehicleRepository.findByLicensePlate(request.getLicensePlate());

        if (licenseCheck.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle already exists!");
        }

        Vehicle vehicle = new Vehicle(request.getLicensePlate(), request.getType(), request.getOwnerName());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return new VehicleResponse(
                savedVehicle.getLicensePlate(),
                savedVehicle.getType(),
                savedVehicle.getOwnerName()
        );
    }

    public String checkInVehicle(String plateNumber, String parkingLocation) {
        Vehicle vehicle = vehicleRepository.findByLicensePlate(plateNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicle.getParking() != null) {
            return "Vehicle " + plateNumber + " is already parked in parking lot " + vehicle.getParking().getLocation();
        }

        Parking parkingLot = parkinglotRepository.findByLocation(parkingLocation)
                .orElseThrow(() -> new RuntimeException("Parking Lot not found"));

        if (parkingLot.isFull()) {
            throw new RuntimeException("Parking lot is full!");
        }

        vehicle.setParking(parkingLot);
        parkingLot.parkVehicle();
        vehicleRepository.save(vehicle);
        parkinglotRepository.save(parkingLot);

        return "Vehicle " + plateNumber + " checked into parking lot " + parkingLot.getLocation();
    }


    public String checkOutVehicle(String plateNumber) {
        Vehicle vehicle = vehicleRepository.findByLicensePlate(plateNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Parking parkingLot = vehicle.getParking();
        if (parkingLot == null) {
            throw new RuntimeException("Vehicle is not parked in any lot");
        }

        parkingLot.removeVehicle();
        vehicle.setParking(null);
        vehicleRepository.save(vehicle);
        parkinglotRepository.save(parkingLot);

        return "Vehicle " + plateNumber + " checked out from parking lot " + parkingLot.getLocation();
    }

    public String viewOccupancy(UUID parkingLotId) {
        Parking parkingLot = parkinglotRepository.findById(parkingLotId)
                .orElseThrow(() -> new RuntimeException("Parking Lot not found"));

        return "Parking Lot " + parkingLot.getLocation() + " has " +
                parkingLot.getOccupied() + "/" + parkingLot.getCapacity() + " occupied spaces.";
    }

    public List<Vehicle> viewParkedVehicles(UUID parkingLotId) {
        Parking parkingLot = parkinglotRepository.findById(parkingLotId)
                .orElseThrow(() -> new RuntimeException("Parking Lot not found"));

        return parkingLot.getParkedVehicles();
    }

    public List<Parking> getAllParkingLots() {
        return parkinglotRepository.findAll();
    }

    public int getAvailableSpaces(String location) {
        Optional<Parking> parkingLot = parkinglotRepository.findByLocation(location);
        return parkingLot.map(lot -> lot.getCapacity() - lot.getOccupied()).orElse(0);
    }
}
