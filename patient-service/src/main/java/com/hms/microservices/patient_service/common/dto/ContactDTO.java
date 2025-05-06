package com.hms.microservices.patient_service.common.dto;

import java.util.UUID;

import com.hms.microservices.patient_service.common.enums.ContactType;

import lombok.Data;

@Data
public class ContactDTO {
    private UUID id;
    private ContactType type;
    private String value;
}
