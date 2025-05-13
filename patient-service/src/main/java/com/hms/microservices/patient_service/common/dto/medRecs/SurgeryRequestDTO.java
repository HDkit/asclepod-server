package com.hms.microservices.patient_service.common.dto.medRecs;

import java.time.Instant;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hms.microservices.patient_service.common.annotations.MedRecSubtype;
import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;
import com.hms.microservices.patient_service.common.enums.MedRecType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonDeserialize(using = JsonDeserializer.None.class)
@MedRecSubtype(MedRecType.SURGERY)
@Data
@EqualsAndHashCode(callSuper = true)
public class SurgeryRequestDTO extends MedicalInfoRequestDTO {
    String surgeryType;
    String note;
    Instant scheduledTime;
    Instant startTime;
    Instant endTime;
}
