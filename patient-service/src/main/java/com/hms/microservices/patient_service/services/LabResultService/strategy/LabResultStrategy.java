package com.hms.microservices.patient_service.services.LabResultService.strategy;

import java.util.UUID;

import com.hms.microservices.patient_service.models.LabResult;

public interface LabResultStrategy {
    LabResult getLabResult(UUID id);

    boolean addLabResult(LabResult labResult);
}
