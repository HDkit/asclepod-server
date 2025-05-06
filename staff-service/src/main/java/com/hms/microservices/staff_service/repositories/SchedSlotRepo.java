package com.hms.microservices.staff_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.microservices.staff_service.models.SchedSlot;
import com.hms.microservices.staff_service.models.SchedVer;

import java.util.List;

public interface SchedSlotRepo extends JpaRepository<SchedSlot, String> {
    List<SchedSlot> findBySchedVerId(SchedVer schedVerId);
}
