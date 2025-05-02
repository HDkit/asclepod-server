package com.hms.microservices.patient_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "blood_test")
public class BloodTest extends LabResult {
    @Column(name = "wbc", nullable = false)
    private Short wbc;

    @Column(name = "neutrophyl", nullable = false)
    private Short neutrophyl;

    @Column(name = "lymphocytes", nullable = false)
    private Short lymphocytes;
}
