package com.hms.microservices.patient_service.services.LabResultService.strategy;

import java.util.UUID;

import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResResponseDTO;
import com.hms.microservices.patient_service.models.LabResult;

public interface LabResultStrategy {
    LabResult getLabResult(UUID id);

    boolean addLabResult(LabResult labResult);

    LabResResponseDTO mapLabResult(LabResult labResult);

    LabResult mapLabResultDTO(LabResRequestDTO labResRequestDTO);
}
