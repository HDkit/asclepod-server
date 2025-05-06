package com.hms.microservices.staff_service.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.microservices.staff_service.models.Employee;
import com.hms.microservices.staff_service.models.SchedSlot;
import com.hms.microservices.staff_service.models.SchedVer;
import com.hms.microservices.staff_service.repositories.DepartmentRepo;
import com.hms.microservices.staff_service.repositories.EmployeeRepo;
import com.hms.microservices.staff_service.repositories.PositionRepo;
import com.hms.microservices.staff_service.repositories.SchedSlotRepo;
import com.hms.microservices.staff_service.repositories.SchedVerRepo;

@Service
public class StaffService {
    @Autowired
    SchedVerRepo schedVerRepo;

    @Autowired
    SchedSlotRepo schedSlotRepo;

    @Autowired
    PositionRepo positionRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    // create new employee rec
    public Employee registerEmployee(Employee employee, String depId, String posId) {
        if (depId != null)
            employee.setDepartmentId(departmentRepo.getReferenceById(depId));
        if (posId != null)
            employee.setPosId(positionRepo.getReferenceById(posId));
        employee.setEmployed(true);
        employeeRepo.save(employee);
        return employee;
    }

    // update employee rec
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    // update employee department
    public Employee updateEmployeeDepartment(UUID empId, String depId) {
        Employee employee = employeeRepo.findById(empId).orElse(null);
        if (employee == null) {
            return null;
        }
        employee.setDepartmentId(departmentRepo.getReferenceById(depId));
        return employeeRepo.save(employee);
    }

    // get employee positions
    public Employee updatEmployeePos(UUID empId, String posId) {
        Employee employee = employeeRepo.findById(empId).orElse(null);
        if (employee == null) {
            return null;
        }
        employee.setPosId(positionRepo.getReferenceById(posId));
        return employeeRepo.save(employee);
    }

    // get all employee recs
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(UUID id) {
        return employeeRepo.findById(id).orElse(null);
    }

    // get all doctor ids
    public List<UUID> getAllDoctorIds() {
        return employeeRepo.findAll().stream()
                .filter(employee -> employee.getPosId().getName().equals("Doctor"))
                .map(Employee::getId)
                .toList();
    }

    // get employee schedule at date X
    public SchedSlot getEmployeeSchedule(UUID id, LocalDate date) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null)
            return null;
        SchedVer schedVer = schedVerRepo.findActiveSchedVer(id, date);
        if (schedVer == null)
            return null;
        List<SchedSlot> schedSlots = schedSlotRepo.findBySchedVerId(schedVer);
        if (schedSlots == null || schedSlots.isEmpty())
            return null;
        return schedSlots.stream().filter(slot -> slot.getDow().equals(date.getDayOfWeek())).findFirst().orElse(null);
    }

    public Boolean isEmployeeAvailableDuring(UUID id, LocalDate date, LocalDateTime start, LocalDateTime end) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null)
            return null;
        SchedVer schedVer = schedVerRepo.findActiveSchedVer(id, date);
        if (schedVer == null)
            return null;
        List<SchedSlot> schedSlots = schedSlotRepo.findBySchedVerId(schedVer);
        if (schedSlots == null || schedSlots.isEmpty())
            return null;
        SchedSlot schedSlot = schedSlots.stream().filter(slot -> slot.getDow().equals(date.getDayOfWeek())).findFirst()
                .orElse(null);
        if (schedSlot == null)
            return null;
        LocalDateTime startTime = schedSlot.getTimeStart();
        LocalDateTime endTime = schedSlot.getTimeEnd();

        if (startTime.isBefore(start) && endTime.isAfter(end)) {
            return true;
        } else if (startTime.isAfter(start) && endTime.isBefore(end)) {
            return true;
        } else if (startTime.isAfter(start) && startTime.isBefore(end)) {
            return true;
        } else if (endTime.isAfter(start) && endTime.isBefore(end)) {
            return true;
        }
        return false;
    }
}
