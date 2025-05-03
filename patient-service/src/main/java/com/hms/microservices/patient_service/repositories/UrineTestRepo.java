package com.hms.microservices.patient_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.microservices.patient_service.models.UrineTest;

@Repository
public interface UrineTestRepo extends JpaRepository<UrineTest, UUID> {
    List<UrineTest> findByPatient_Id(UUID patientId);
}
