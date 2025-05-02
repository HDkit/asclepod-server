package com.hms.microservices.patient_service.models;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;

import com.hms.microservices.patient_service.common.enums.Sex;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "patients_info", indexes = {
        @Index(name = "idx_insCode", columnList = "ins_Code"),
        @Index(name = "idx_deleted", columnList = "deleted")
})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "f_name", nullable = false)
    private String firstName;

    @Column(name = "l_name")
    private String lastName;

    @Column(name = "b_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "weight")
    private Short weight;

    @Column(name = "height")
    private Short height;

    @Column(name = "ins_code", length = 15, nullable = false, unique = true)
    private String insCode;

    @Column(name = "deleted")
    @ColumnDefault("false")
    private Boolean deleted = false;

}
