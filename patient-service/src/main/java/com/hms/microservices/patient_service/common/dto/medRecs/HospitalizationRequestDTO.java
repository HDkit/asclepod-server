package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;

import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;

public class HospitalizationRequestDTO extends MedicalInfoRequestDTO {
    Instant admissionTime;
    Instant dischargeTime;
    String admissionReason;
    String dischargeReason;
}
