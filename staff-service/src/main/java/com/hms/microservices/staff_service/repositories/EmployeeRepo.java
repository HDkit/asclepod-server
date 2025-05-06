package com.hms.microservices.staff_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.microservices.staff_service.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

}
