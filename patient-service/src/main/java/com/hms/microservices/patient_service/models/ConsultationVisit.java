package com.hms.microservices.patient_service.models;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "consultation_visit", indexes = {
        @Index(name = "idx_patient_id", columnList = "patient_id"),
        @Index(name = "idx_date", columnList = "date"),
        @Index(name = "idx_department", columnList = "department"),
})
public class ConsultationVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "note")
    private String note;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
}
