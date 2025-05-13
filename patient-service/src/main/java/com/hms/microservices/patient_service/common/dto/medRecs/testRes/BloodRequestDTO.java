package com.hms.microservices.patient_service.common.dto.medRecs.testRes;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hms.microservices.patient_service.common.annotations.TestResSubtype;
import com.hms.microservices.patient_service.common.enums.TestType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonDeserialize(using = JsonDeserializer.None.class)
@TestResSubtype(TestType.BLOOD)
@Data
@EqualsAndHashCode(callSuper = true)
public class BloodRequestDTO extends LabResRequestDTO {
    Short wbc;
    Short neutrophyl;
    Short lymphocytes;
}
