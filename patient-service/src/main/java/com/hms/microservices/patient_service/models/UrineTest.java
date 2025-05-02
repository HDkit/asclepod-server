package com.hms.microservices.patient_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "urine_test")
public class UrineTest extends LabResult {
    @Column(name = "epinephrine", nullable = false)
    private Short epinephrine;

    @Column(name = "norepinephrine", nullable = false)
    private Short metanephrine;
}