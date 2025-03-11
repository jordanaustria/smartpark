package com.assignment.smartpark;

import com.assignment.smartpark.dto.ParkinglotRequest;
import com.assignment.smartpark.dto.ParkinglotResponse;
import com.assignment.smartpark.dto.VehicleRequest;
import com.assignment.smartpark.dto.VehicleResponse;
import com.assignment.smartpark.entity.Parking;
import com.assignment.smartpark.entity.Vehicle;
import com.assignment.smartpark.repository.ParkinglotRepository;
import com.assignment.smartpark.repository.VehicleRepository;
import com.assignment.smartpark.service.SmartParkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SmartParkServiceTest {

    @Mock
    private ParkinglotRepository parkingLotRepository;
    
    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private SmartParkService service;

    private Parking parking;
    private Vehicle vehicle;
    private UUID parkingLotId;
    
    @BeforeEach
    void setUp() {
        parkingLotId = UUID.randomUUID();
        parking = new Parking(parkingLotId, "Downtown", 10, 0);
        vehicle = new Vehicle("ABC-123", "Car", "Jordan Austria");
    }

    @Test
    void testRegisterParkingLot() {
        when(parkingLotRepository.save(any(Parking.class))).thenReturn(parking);

        ParkinglotResponse savedLot = service.registerParkinglot(new ParkinglotRequest("Downtown", 10, 0));
        
        assertNotNull(savedLot);
        assertEquals("Downtown", savedLot.getLocation());
        assertEquals(10, savedLot.getCapacity());
    }

    @Test
    void testRegisterVehicle() {
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);
        
        VehicleResponse registeredVehicle = service.registerVehicle(new VehicleRequest("ABC-123", "Car", "Jordan Austria"));
        
        assertNotNull(registeredVehicle);
        assertEquals("ABC-123", registeredVehicle.getLicensePlate());
    }

    @Test
    void testCheckInVehicle_Success() {
        when(vehicleRepository.findByLicensePlate("ABC-123")).thenReturn(Optional.of(vehicle));
        when(parkingLotRepository.findByLocation(parking.getLocation())).thenReturn(Optional.of(parking));
        
        String response = service.checkInVehicle("ABC-123", parking.getLocation());
        
        assertEquals("Vehicle ABC-123 checked into parking lot Downtown", response);
        assertEquals(1, parking.getOccupied());
    }

    @Test
    void testCheckInVehicle_FullLot() {
        parking.setOccupied(10);
        when(vehicleRepository.findByLicensePlate("ABC-123")).thenReturn(Optional.of(vehicle));
        when(parkingLotRepository.findById(parkingLotId)).thenReturn(Optional.of(parking));
        
        Exception exception = assertThrows(RuntimeException.class, () -> service.checkInVehicle("ABC-123", parking.getLocation()));
        
        assertEquals("Parking lot is full!", exception.getMessage());
    }

    @Test
    void testCheckOutVehicle_Success() {
        vehicle.setParking(parking);
        parking.setOccupied(5);
        when(vehicleRepository.findByLicensePlate("ABC-123")).thenReturn(Optional.of(vehicle));
        
        String response = service.checkOutVehicle("ABC-123");
        
        assertEquals("Vehicle ABC-123 checked out from parking lot Downtown", response);
        assertEquals(4, parking.getOccupied());
    }
}
