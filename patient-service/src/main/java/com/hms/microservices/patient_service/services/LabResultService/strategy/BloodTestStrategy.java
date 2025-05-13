package com.hms.microservices.patient_service.services.LabResultService.strategy;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hms.microservices.patient_service.common.annotations.TestStrategy;
import com.hms.microservices.patient_service.common.dto.mapper.MedicalRecordMapper;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.BloodRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.BloodResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResRequestDTO;
import com.hms.microservices.patient_service.common.enums.TestType;
import com.hms.microservices.patient_service.models.BloodTest;
import com.hms.microservices.patient_service.models.LabResult;
import com.hms.microservices.patient_service.repositories.BloodTestRepo;

@TestStrategy(TestType.BLOOD)
@Component
public final class BloodTestStrategy implements LabResultStrategy {
    @Autowired
    BloodTestRepo bloodTestRepo;

    @Autowired
    MedicalRecordMapper medicalRecordMapper;

    @Override
    public BloodTest getLabResult(UUID id) {
        return bloodTestRepo.findById(id).orElse(null);
    }

    @Override
    public boolean addLabResult(LabResult labResult) {
        bloodTestRepo.save((BloodTest) labResult);
        return true;
    }

    @Override
    public BloodResponseDTO mapLabResult(LabResult labResult) {
        return medicalRecordMapper.toBloodResponseDTO((BloodTest) labResult);
    }

    @Override
    public BloodTest mapLabResultDTO(LabResRequestDTO labResRequestDTO) {
        return medicalRecordMapper.toBloodTest((BloodRequestDTO) labResRequestDTO);
    }
}
