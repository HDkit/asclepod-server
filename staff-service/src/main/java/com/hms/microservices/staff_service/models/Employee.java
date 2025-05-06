package com.hms.microservices.staff_service.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department departmentId;

    @ManyToOne
    @JoinColumn(name = "pos_id")
    private Position posId;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "employed", nullable = false)
    private boolean employed = false;
}
