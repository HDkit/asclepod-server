package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;

import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;

public class SurgeryRequestDTO extends MedicalInfoRequestDTO {
    String type;
    String note;
    Instant scheduledTime;
    Instant startTime;
    Instant endTime;
}
