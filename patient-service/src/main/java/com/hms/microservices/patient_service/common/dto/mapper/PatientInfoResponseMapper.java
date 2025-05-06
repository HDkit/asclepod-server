package com.hms.microservices.patient_service.common.dto.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.hms.microservices.patient_service.common.dto.ContactDTO;
import com.hms.microservices.patient_service.common.dto.PatientInfoRequestDTO;
import com.hms.microservices.patient_service.common.dto.PatientInfoResponseDTO;
import com.hms.microservices.patient_service.models.ContactInfo;
import com.hms.microservices.patient_service.models.Patient;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PatientInfoResponseMapper {
    // patient response DTO to patient
    @Mapping(target = ".", source = "patient")
    @Mapping(target = "contacts", source = "contactInfosList")
    PatientInfoResponseDTO toPatientInfoResponseDTO(Patient patient, List<ContactInfo> contactInfosList);

    // patient request to patient
    @Mapping(target = ".", source = "patientInfoRequestDTO")
    @Mapping(target = "id", ignore = true)
    Patient toPatient(PatientInfoRequestDTO patientInfoRequestDTO);

    // patient request to update patient
    @Mapping(target = ".", source = "patientInfoRequestDTO")
    @Mapping(target = "id", ignore = true)
    void updatePatientFromDTO(PatientInfoRequestDTO patientInfoRequestDTO, @MappingTarget Patient patient);

    @Mapping(target = "contactType", source = "type")
    @Mapping(target = "contactVal", source = "value")
    @Mapping(target = "patient", ignore = true)
    ContactInfo toContactInfo(ContactDTO contactDTO);

    // reverse
    @Mapping(target = "type", source = "contactType")
    @Mapping(target = "value", source = "contactVal")
    ContactDTO toContactDTO(ContactInfo contactInfo);

    // patient request/contact dtos to contact infos list
    List<ContactInfo> toContactInfosList(List<ContactDTO> contactDTOs, @Context Patient patient);

    @AfterMapping
    default void mapContactInfo(@MappingTarget List<ContactInfo> contactInfos, @Context Patient patient) {
        for (ContactInfo contactInfo : contactInfos)
            contactInfo.setPatient(patient);
    }
}
