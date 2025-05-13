package com.hms.microservices.patient_service.common.dto;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hms.microservices.patient_service.common.enums.MedRecType;
import com.hms.microservices.patient_service.common.util.deserializer.MedInfoRequestDTODeserializer;

import lombok.Data;

@JsonDeserialize(using = MedInfoRequestDTODeserializer.class)
@Data
public abstract class MedicalInfoRequestDTO {
    private MedRecType type;
    private UUID patientId;
}