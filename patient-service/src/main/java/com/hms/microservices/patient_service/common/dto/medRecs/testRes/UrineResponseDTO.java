package com.hms.microservices.patient_service.common.dto.medRecs.testRes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UrineResponseDTO extends LabResResponseDTO {
    Short epinephrine;
    Short metanephrine;
}
