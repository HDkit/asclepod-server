package com.hms.microservices.patient_service.common.dto.medRecs.testRes;

import java.time.Instant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hms.microservices.patient_service.common.annotations.MedRecSubtype;
import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;
import com.hms.microservices.patient_service.common.enums.MedRecType;
import com.hms.microservices.patient_service.common.enums.TestType;
import com.hms.microservices.patient_service.common.util.deserializer.LabResRequestDTODeserializer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonDeserialize(using = LabResRequestDTODeserializer.class)
@MedRecSubtype(MedRecType.TEST)
@Data
@EqualsAndHashCode(callSuper = true)
public class LabResRequestDTO extends MedicalInfoRequestDTO {
    TestType testType;
    Instant createdAt;
}
