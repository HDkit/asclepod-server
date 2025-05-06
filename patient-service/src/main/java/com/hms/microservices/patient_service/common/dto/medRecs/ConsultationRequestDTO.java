package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;

import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;

public class ConsultationRequestDTO extends MedicalInfoRequestDTO {
    Instant date;
    String department;
    String reason;
    String note;
}
