package com.hms.microservices.patient_service.services.LabResultService.strategy;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.hms.microservices.patient_service.models.LabResult;
import com.hms.microservices.patient_service.models.UrineTest;
import com.hms.microservices.patient_service.repositories.UrineTestRepo;

public class UrineTestStrategy implements LabResultStrategy {
    @Autowired
    UrineTestRepo urineTestRepo;

    @Override
    public UrineTest getLabResult(UUID id) {
        return urineTestRepo.findById(id).orElse(null);
    }

    @Override
    public boolean addLabResult(LabResult labResult) {
        urineTestRepo.save((UrineTest) labResult);
        return true;
    }
}
