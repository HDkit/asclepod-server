package com.hms.microservices.patient_service.common.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.hms.microservices.patient_service.common.dto.medRecs.AllergyRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.AllergyResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.ConsultationRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.ConsultationResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.HospitalizationRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.HospitalizationResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.SurgeryRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.SurgeryResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.BloodRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.BloodResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.UrineRequestDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.UrineResponseDTO;
import com.hms.microservices.patient_service.models.Allergy;
import com.hms.microservices.patient_service.models.BloodTest;
import com.hms.microservices.patient_service.models.ConsultationVisit;
import com.hms.microservices.patient_service.models.Hospitalization;
import com.hms.microservices.patient_service.models.Surgery;
import com.hms.microservices.patient_service.models.UrineTest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MedicalRecordMapper {
    // allergy
    @Mapping(target = ".", source = "allergy")
    @Mapping(target = "allergen", source = "allergy.name")
    @Mapping(target = "patientId", source = "allergy.patient.id")
    @Mapping(target = "type", constant = "ALLERGY")
    AllergyResponseDTO toAllergyResponseDTO(Allergy allergy);

    @Mapping(target = ".", source = "allergyRequestDTO")
    @Mapping(target = "name", source = "allergyRequestDTO.allergen")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "id", ignore = true)
    Allergy toAllergy(AllergyRequestDTO allergyRequestDTO);

    // consultation
    @Mapping(target = ".", source = "consultationVisit")
    @Mapping(target = "patientId", source = "consultationVisit.patient.id")
    @Mapping(target = "type", constant = "CONSULTATION")
    ConsultationResponseDTO toConsultationResponseDTO(ConsultationVisit consultationVisit);

    @Mapping(target = ".", source = "consultationRequestDTO")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "id", ignore = true)
    ConsultationVisit toConsultationVisit(ConsultationRequestDTO consultationRequestDTO);

    // hospitalization
    @Mapping(target = ".", source = "hospitalization")
    @Mapping(target = "patientId", source = "hospitalization.patient.id")
    @Mapping(target = "type", constant = "HOSPITALIZATION")
    HospitalizationResponseDTO toHospitalizationResponseDTO(Hospitalization hospitalization);

    @Mapping(target = ".", source = "hospitalizationRequestDTO")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "id", ignore = true)
    Hospitalization toHospitalization(HospitalizationRequestDTO hospitalizationRequestDTO);

    // surgery
    @Mapping(target = ".", source = "surgery")
    @Mapping(target = "surgeryType", source = "surgery.type")
    @Mapping(target = "patientId", source = "surgery.patient.id")
    @Mapping(target = "type", constant = "SURGERY")
    SurgeryResponseDTO toSurgeryResponseDTO(Surgery surgery);

    @Mapping(target = ".", source = "surgeryRequestDTO")
    @Mapping(target = "type", source = "surgeryRequestDTO.surgeryType")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "id", ignore = true)
    Surgery toSurgery(SurgeryRequestDTO surgeryRequestDTO);

    // lab result
    // blood, urine
    @Mapping(target = ".", source = "bloodTest")
    @Mapping(target = "patientId", source = "bloodTest.patient.id")
    @Mapping(target = "type", constant = "TEST")
    BloodResponseDTO toBloodResponseDTO(BloodTest bloodTest);

    @Mapping(target = ".", source = "bloodRequestDTO")
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    BloodTest toBloodTest(BloodRequestDTO bloodRequestDTO);

    @Mapping(target = ".", source = "urineTest")
    @Mapping(target = "patientId", source = "urineTest.patient.id")
    @Mapping(target = "type", constant = "TEST")
    UrineResponseDTO toUrineResponseDTO(UrineTest urineTest);

    @Mapping(target = ".", source = "urineRequestDTO")
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    UrineTest toUrineTest(UrineRequestDTO urineRequestDTO);
}
