package com.hms.microservices.patient_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.microservices.patient_service.models.Surgery;

@Repository
public interface SurgeryRepo extends JpaRepository<Surgery, UUID> {
    List<Surgery> findByPatient_Id(UUID patientId);
}
