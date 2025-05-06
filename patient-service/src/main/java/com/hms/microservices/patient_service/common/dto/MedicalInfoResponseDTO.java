package com.hms.microservices.patient_service.common.dto;

import java.util.UUID;

import com.hms.microservices.patient_service.common.enums.MedRecType;

import lombok.Data;

@Data
public abstract class MedicalInfoResponseDTO {
    private MedRecType type;
    private UUID patientId;
}
