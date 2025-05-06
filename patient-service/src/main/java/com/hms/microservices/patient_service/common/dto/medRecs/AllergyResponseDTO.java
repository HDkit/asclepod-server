package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;
import java.util.UUID;

import com.hms.microservices.patient_service.common.dto.MedicalInfoResponseDTO;

public class AllergyResponseDTO extends MedicalInfoResponseDTO {
    UUID id;
    String allergen;
    Instant createdAt;
}
