package com.hms.microservices.patient_service.common.errors;

import java.util.UUID;

public class PatientNotFoundError extends RuntimeException {
    public PatientNotFoundError(UUID id) {
        super("Could not find patient with id: " + id);
    }
}
