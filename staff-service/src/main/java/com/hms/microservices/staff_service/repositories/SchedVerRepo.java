package com.hms.microservices.staff_service.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.microservices.staff_service.models.SchedVer;

public interface SchedVerRepo extends JpaRepository<SchedVer, String> {
    @Query(value = """
            SELECT * FROM sched_ver
            WHERE employee_id = :employeeId
            AND :dateX >= start_date
            AND (:dateX <= end_date OR end_date IS NULL)
            LIMIT 1
            """, nativeQuery = true)
    SchedVer findActiveSchedVer(
            @Param("employeeId") UUID employeeId,
            @Param("dateX") LocalDate dateX);
}
