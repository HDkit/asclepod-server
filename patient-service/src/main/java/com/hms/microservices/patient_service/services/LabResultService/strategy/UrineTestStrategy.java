package com.hms.microservices.patient_service.services.LabResultService.strategy;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hms.microservices.patient_service.common.annotations.TestStrategy;
import com.hms.microservices.patient_service.common.dto.mapper.MedicalRecordMapper;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.UrineRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.UrineResponseDTO;
import com.hms.microservices.patient_service.common.enums.TestType;
import com.hms.microservices.patient_service.models.LabResult;
import com.hms.microservices.patient_service.models.UrineTest;
import com.hms.microservices.patient_service.repositories.UrineTestRepo;

@TestStrategy(TestType.URINE)
@Component
public final class UrineTestStrategy implements LabResultStrategy {
    @Autowired
    UrineTestRepo urineTestRepo;

    @Autowired
    MedicalRecordMapper medicalRecordMapper;

    @Override
    public UrineTest getLabResult(UUID id) {
        return urineTestRepo.findById(id).orElse(null);
    }

    @Override
    public boolean addLabResult(LabResult labResult) {
        urineTestRepo.save((UrineTest) labResult);
        return true;
    }

    @Override
    public UrineResponseDTO mapLabResult(LabResult labResult) {
        return medicalRecordMapper.toUrineResponseDTO((UrineTest) labResult);
    }

    @Override
    public UrineTest mapLabResultDTO(LabResRequestDTO labResRequestDTO) {
        return medicalRecordMapper.toUrineTest((UrineRequestDTO) labResRequestDTO);
    }
}
