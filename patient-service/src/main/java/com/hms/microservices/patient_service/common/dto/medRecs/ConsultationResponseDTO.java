package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;
import java.util.UUID;

import com.hms.microservices.patient_service.common.dto.MedicalInfoResponseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConsultationResponseDTO extends MedicalInfoResponseDTO {
    UUID id;
    Instant date;
    String department;
    String reason;
    String note;
    Instant createdAt;
}
