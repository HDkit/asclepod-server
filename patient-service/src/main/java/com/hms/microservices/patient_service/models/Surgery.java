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
@Table(name = "surgery", indexes = {
        @Index(name = "idx_patient_id", columnList = "patient_id"),
        @Index(name = "idx_scheduled_time", columnList = "scheduled_time"),
        @Index(name = "idx_start_time", columnList = "start_time"),
        @Index(name = "idx_end_time", columnList = "end_time")
})
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "note")
    private String note;

    @Column(name = "scheduled_time", nullable = false)
    private Instant scheduledTime;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
}
