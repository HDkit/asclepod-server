package com.hms.microservices.patient_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.microservices.patient_service.models.ContactInfo;
import com.hms.microservices.patient_service.models.Patient;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfo, UUID> {
    List<ContactInfo> findByPatient(Patient patient);
}
