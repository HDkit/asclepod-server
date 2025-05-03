package com.hms.microservices.patient_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.microservices.patient_service.models.Allergy;

@Repository
public interface AllergyRepo extends JpaRepository<Allergy, UUID> {
    List<Allergy> findByPatient_Id(UUID patientId);
}
