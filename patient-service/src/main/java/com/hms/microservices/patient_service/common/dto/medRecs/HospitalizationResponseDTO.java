package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;
import java.util.UUID;

import com.hms.microservices.patient_service.common.dto.MedicalInfoResponseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HospitalizationResponseDTO extends MedicalInfoResponseDTO {
    UUID id;
    Instant admissionTime;
    Instant dischargeTime;
    String admissionReason;
    String dischargeReason;
    Instant createdAt;
}
