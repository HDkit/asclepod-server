package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;
import java.util.UUID;

import com.hms.microservices.patient_service.common.dto.MedicalInfoResponseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SurgeryResponseDTO extends MedicalInfoResponseDTO {
    UUID id;
    String surgeryType;
    String note;
    Instant scheduledTime;
    Instant startTime;
    Instant endTime;
    Instant createdAt;
}
