package com.hms.microservices.patient_service.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.hms.microservices.patient_service.common.enums.Sex;

import lombok.Data;

@Data
public class PatientInfoDTO {
    private UUID id;
    private String lastName;
    private String firstName;
    private LocalDate birthdate;
    private Sex sex;
    private Short weight;
    private Short height;
    private String insCode;
    private Boolean deleted;
    private String[] phoneNumbers;
    private String[] mailAddrs;
}
