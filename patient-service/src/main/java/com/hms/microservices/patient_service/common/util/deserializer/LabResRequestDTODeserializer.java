package com.hms.microservices.patient_service.common.util.deserializer;

import com.hms.microservices.patient_service.common.annotations.TestResSubtype;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResRequestDTO;

public class LabResRequestDTODeserializer extends CustomDeserializer<LabResRequestDTO> {
    public LabResRequestDTODeserializer() {
        super(LabResRequestDTO.class, "testType", TestResSubtype.class);
    }
}
