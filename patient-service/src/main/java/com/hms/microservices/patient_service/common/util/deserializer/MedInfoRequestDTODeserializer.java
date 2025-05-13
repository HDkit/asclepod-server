package com.hms.microservices.patient_service.common.util.deserializer;

import com.hms.microservices.patient_service.common.annotations.MedRecSubtype;
import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;

public class MedInfoRequestDTODeserializer extends CustomDeserializer<MedicalInfoRequestDTO> {
    public MedInfoRequestDTODeserializer() {
        super(MedicalInfoRequestDTO.class, "type", MedRecSubtype.class);
    }
}
