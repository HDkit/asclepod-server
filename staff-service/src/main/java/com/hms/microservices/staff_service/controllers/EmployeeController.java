package com.hms.microservices.staff_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.microservices.staff_service.models.Employee;
import com.hms.microservices.staff_service.models.SchedSlot;
import com.hms.microservices.staff_service.services.StaffService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/staff")
public class EmployeeController {
    @Autowired
    private StaffService staffService;

    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee entity, @RequestParam String depId,
            @RequestParam String posId) {
        return staffService.registerEmployee(entity, depId, posId);
    }

    @GetMapping("")
    public List<Employee> getAll() {
        return staffService.getAllEmployees();
    }

    @GetMapping("/docs")
    public List<UUID> getDocs() {
        return staffService.getAllDoctorIds();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable UUID id) {
        return staffService.getEmployeeById(id);
    }

    @PostMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable UUID id) {
        return staffService.getEmployeeById(id);
    }

    @GetMapping("/{id}/sched")
    public SchedSlot getMethodName(@PathVariable UUID id, @RequestParam LocalDate date) {
        return staffService.getEmployeeSchedule(id, date);
    }

    @GetMapping("/{id}/avail")
    public Boolean getMethodName(@PathVariable UUID id, @RequestParam LocalDate date, @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return staffService.isEmployeeAvailableDuring(id, date, start, end);
    }

}
