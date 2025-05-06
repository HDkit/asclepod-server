package com.hms.microservices.staff_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.microservices.staff_service.models.Department;

public interface DepartmentRepo extends JpaRepository<Department, String> {

}
