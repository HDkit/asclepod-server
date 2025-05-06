package com.hms.microservices.patient_service.common.dto.medRecs.testRes;

import java.time.Instant;

import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;
import com.hms.microservices.patient_service.common.enums.TestType;

public class LabResRequestDTO extends MedicalInfoRequestDTO {
    TestType testType;
    Instant createdAt;
}
