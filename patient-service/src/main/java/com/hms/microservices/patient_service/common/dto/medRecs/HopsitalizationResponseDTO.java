package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;
import java.util.UUID;

import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;

public class HopsitalizationResponseDTO extends MedicalInfoRequestDTO {
    UUID id;
    Instant admissionTime;
    Instant dischargeTime;
    String admissionReason;
    String dischargeReason;
    Instant createdAt;
}
