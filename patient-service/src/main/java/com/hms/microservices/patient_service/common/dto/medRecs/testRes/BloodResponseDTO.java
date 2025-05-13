package com.hms.microservices.patient_service.common.dto.medRecs.testRes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BloodResponseDTO extends LabResResponseDTO {
    Short wbc;
    Short neutrophyl;
    Short lymphocytes;
}
