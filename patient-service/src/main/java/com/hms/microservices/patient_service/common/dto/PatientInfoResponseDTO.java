package com.hms.microservices.patient_service.common.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.hms.microservices.patient_service.common.enums.Sex;

import lombok.Data;

@Data
public class PatientInfoResponseDTO {
    private UUID id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private Sex sex;
    private Short weight;
    private Short height;
    private String insCode;
    private Boolean deleted;
    private List<ContactDTO> contacts;
}
