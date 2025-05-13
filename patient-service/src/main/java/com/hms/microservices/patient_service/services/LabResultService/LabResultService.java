package com.hms.microservices.patient_service.services.LabResultService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResResponseDTO;
import com.hms.microservices.patient_service.models.LabResult;
import com.hms.microservices.patient_service.repositories.LabResultRepo;
import com.hms.microservices.patient_service.services.LabResultService.factory.LabResultStratFactory;
import com.hms.microservices.patient_service.services.LabResultService.strategy.LabResultStrategy;

// revise the type of errors thrown in the methods
@Service
public class LabResultService {

    @Autowired
    LabResultRepo labResultRepo;

    @Autowired
    LabResultStratFactory stratFactory;

    public LabResult getLabResultDetail(UUID id) {
        LabResult res = labResultRepo.findById(id).orElse(null);
        LabResultStrategy strategy = stratFactory.getStrategy(res.getTestType());
        return (strategy != null) ? strategy.getLabResult(id) : null;
    }

    public boolean addLabResult(LabResult labResult) {
        LabResultStrategy strategy = stratFactory.getStrategy(labResult.getTestType());
        if (strategy != null) {
            return strategy.addLabResult(labResult);
        } else {
            throw new IllegalArgumentException("No strategy found for test type: " + labResult.getTestType());
        }
    }

    public LabResResponseDTO mapLabResult(LabResult labResult) {
        LabResultStrategy strategy = stratFactory.getStrategy(labResult.getTestType());
        if (strategy != null) {
            return strategy.mapLabResult(labResult);
        } else {
            throw new IllegalArgumentException("No strategy found for test type: " + labResult.getTestType());
        }
    }

    public LabResult mapLabResultDTO(LabResRequestDTO labResRequestDTO) {
        LabResultStrategy strategy = stratFactory.getStrategy(labResRequestDTO.getTestType());
        if (strategy != null) {
            return strategy.mapLabResultDTO(labResRequestDTO);
        } else {
            throw new IllegalArgumentException("No strategy found for test type: " + labResRequestDTO.getTestType());
        }
    }
}