package com.hms.microservices.patient_service.services.MedicalRecordService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;
import com.hms.microservices.patient_service.common.dto.MedicalInfoResponseDTO;
import com.hms.microservices.patient_service.common.dto.mapper.MedicalRecordMapper;
import com.hms.microservices.patient_service.common.dto.medRecs.AllergyRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.AllergyResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.ConsultationRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.ConsultationResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.HospitalizationRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.HospitalizationResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.SurgeryRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.SurgeryResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResResponseDTO;
import com.hms.microservices.patient_service.common.enums.MedRecType;
import com.hms.microservices.patient_service.models.Allergy;
import com.hms.microservices.patient_service.models.ConsultationVisit;
import com.hms.microservices.patient_service.models.Hospitalization;
import com.hms.microservices.patient_service.models.LabResult;
import com.hms.microservices.patient_service.models.Patient;
import com.hms.microservices.patient_service.models.Surgery;
import com.hms.microservices.patient_service.repositories.AllergyRepo;
import com.hms.microservices.patient_service.repositories.ConsultationVisitRepo;
import com.hms.microservices.patient_service.repositories.HospitalizationRepo;
import com.hms.microservices.patient_service.repositories.LabResultRepo;
import com.hms.microservices.patient_service.repositories.PatientRepo;
import com.hms.microservices.patient_service.repositories.SurgeryRepo;
import com.hms.microservices.patient_service.services.LabResultService.LabResultService;

@Service
public class MedicalRecordService {
    @Autowired
    PatientRepo patientRepo;

    @Autowired
    AllergyRepo allergyRepo;

    @Autowired
    ConsultationVisitRepo consultationVisitRepo;

    @Autowired
    HospitalizationRepo hospitalizationRepo;

    @Autowired
    SurgeryRepo surgeryRepo;

    @Autowired
    LabResultRepo labResultRepo;

    @Autowired
    LabResultService labResultService;

    @Autowired
    MedicalRecordMapper medicalRecordMapper;

    public List<AllergyResponseDTO> getAllergy(UUID pid) {
        List<Allergy> allergies = allergyRepo.findByPatient_Id(pid);
        List<AllergyResponseDTO> allergyResponseDTOs = allergies
                .stream()
                .map(medicalRecordMapper::toAllergyResponseDTO)
                .toList();
        return allergyResponseDTOs;
    }

    public List<ConsultationResponseDTO> getConsultation(UUID pid) {
        List<ConsultationVisit> consultations = consultationVisitRepo.findByPatient_Id(pid);
        List<ConsultationResponseDTO> consultationResponseDTOs = consultations
                .stream()
                .map(medicalRecordMapper::toConsultationResponseDTO)
                .toList();
        return consultationResponseDTOs;
    }

    public List<HospitalizationResponseDTO> getHospitalization(UUID pid) {
        List<Hospitalization> hospitalizations = hospitalizationRepo.findByPatient_Id(pid);
        List<HospitalizationResponseDTO> hospitalizationResponseDTOs = hospitalizations
                .stream()
                .map(medicalRecordMapper::toHospitalizationResponseDTO)
                .toList();
        return hospitalizationResponseDTOs;
    }

    public List<SurgeryResponseDTO> getSurgery(UUID pid) {
        List<Surgery> surgeries = surgeryRepo.findByPatient_Id(pid);
        List<SurgeryResponseDTO> surgeryResponseDTOs = surgeries
                .stream()
                .map(medicalRecordMapper::toSurgeryResponseDTO)
                .toList();
        return surgeryResponseDTOs;
    }

    public List<LabResResponseDTO> getLabResult(UUID pid) {
        List<LabResult> labResults = labResultRepo.findByPatient_Id(pid);
        List<LabResResponseDTO> labResResponseDTOs = labResults
                .stream()
                .map(labResultService::mapLabResult)
                .toList();
        return labResResponseDTOs;
    }

    public MedicalInfoResponseDTO putMedicalInfo(UUID pid, MedicalInfoRequestDTO medicalInfoDTO) {
        Patient patient = patientRepo.getReferenceById(pid);
        switch (medicalInfoDTO.getType()) {
            case MedRecType.ALLERGY:
                Allergy allergy = medicalRecordMapper.toAllergy((AllergyRequestDTO) medicalInfoDTO);
                allergy.setPatient(patient);
                return medicalRecordMapper.toAllergyResponseDTO(allergyRepo.save(allergy));

            case MedRecType.CONSULTATION:
                ConsultationVisit consultationVisit = medicalRecordMapper
                        .toConsultationVisit((ConsultationRequestDTO) medicalInfoDTO);
                consultationVisit.setPatient(patient);
                return medicalRecordMapper.toConsultationResponseDTO(consultationVisitRepo.save(consultationVisit));

            case MedRecType.HOSPITALIZATION:
                Hospitalization hospitalization = medicalRecordMapper
                        .toHospitalization((HospitalizationRequestDTO) medicalInfoDTO);
                hospitalization.setPatient(patient);
                return medicalRecordMapper.toHospitalizationResponseDTO(hospitalizationRepo.save(hospitalization));

            case MedRecType.SURGERY:
                Surgery surgery = medicalRecordMapper.toSurgery((SurgeryRequestDTO) medicalInfoDTO);
                surgery.setPatient(patient);
                return medicalRecordMapper.toSurgeryResponseDTO(surgeryRepo.save(surgery));

            case MedRecType.TEST:
                LabResult labResult = labResultService.mapLabResultDTO((LabResRequestDTO) medicalInfoDTO);
                labResult.setPatient(patient);
                return labResultService.mapLabResult(labResultRepo.save(labResult));

            default:
                return null;
        }
    }
}
