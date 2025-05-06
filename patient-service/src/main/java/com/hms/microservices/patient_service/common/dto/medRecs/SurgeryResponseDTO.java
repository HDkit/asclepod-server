package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;
import java.util.UUID;

import com.hms.microservices.patient_service.common.dto.MedicalInfoResponseDTO;

public class SurgeryResponseDTO extends MedicalInfoResponseDTO {
    UUID id;
    String type;
    String note;
    Instant scheduledTime;
    Instant startTime;
    Instant endTime;
    Instant createdAt;
}
