package com.hms.microservices.staff_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.microservices.staff_service.models.Position;

public interface PositionRepo extends JpaRepository<Position, String> {

}
