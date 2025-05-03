package com.hms.microservices.patient_service.service.LabResultService.strategy;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hms.microservices.patient_service.models.BloodTest;
import com.hms.microservices.patient_service.models.LabResult;
import com.hms.microservices.patient_service.repositories.BloodTestRepo;

@Component
public class BloodTestStrategy implements LabResultStrategy {
    @Autowired
    BloodTestRepo bloodTestRepo;

    @Override
    public BloodTest getLabResult(UUID id) {
        return bloodTestRepo.findById(id).orElse(null);
    }

    @Override
    public boolean addLabResult(LabResult labResult) {
        bloodTestRepo.save((BloodTest) labResult);
        return true;
    }
}
