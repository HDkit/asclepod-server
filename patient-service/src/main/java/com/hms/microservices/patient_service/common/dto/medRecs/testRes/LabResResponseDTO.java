package com.hms.microservices.patient_service.common.dto.medRecs.testRes;

import java.time.Instant;
import java.util.UUID;

import com.hms.microservices.patient_service.common.dto.MedicalInfoResponseDTO;
import com.hms.microservices.patient_service.common.enums.TestType;

public class LabResResponseDTO extends MedicalInfoResponseDTO {
    UUID id;
    TestType testType;
    Instant createdAt;
}
