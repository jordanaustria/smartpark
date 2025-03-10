package com.assignment.smartpark.repository;

import com.assignment.smartpark.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkinglotRepository extends JpaRepository<Parking, UUID> {

    Optional<Parking> findByLocation(String location);

}
