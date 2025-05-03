package com.hms.microservices.patient_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.microservices.patient_service.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, UUID> {
    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
