package com.hms.microservices.staff_service.models;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sched_slot")
public class SchedSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "sched_ver_id", nullable = false)
    private SchedVer schedVerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "dow", nullable = false)
    private DayOfWeek dow;

    @Column(name = "timeStart", nullable = false)
    private LocalDateTime timeStart;

    @Column(name = "timeEnd")
    private LocalDateTime timeEnd;
}
