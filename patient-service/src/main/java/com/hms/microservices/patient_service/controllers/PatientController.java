package com.hms.microservices.patient_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hms.microservices.patient_service.dto.PatientInfoDTO;
import com.hms.microservices.patient_service.models.Allergy;
import com.hms.microservices.patient_service.models.ConsultationVisit;
import com.hms.microservices.patient_service.models.Hospitalization;
import com.hms.microservices.patient_service.models.LabResult;
import com.hms.microservices.patient_service.models.Surgery;
import com.hms.microservices.patient_service.service.MedicalRecordService;
import com.hms.microservices.patient_service.service.PatientInfoService;
import com.hms.microservices.patient_service.service.LabResultService.LabResultService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/patients/")
public class PatientController {

    @Autowired
    private PatientInfoService patientInfoService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private LabResultService labResultService;

    // create new patient
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientInfoDTO postNewPatient(@RequestBody PatientInfoDTO patientInfoDTO) {
        return patientInfoService.registerPatient(patientInfoDTO);
    }

    // delete patient
    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public PatientInfoDTO deletePatient(@PathVariable UUID id) {
        return patientInfoService.deletePatient(id);
    }

    // get all patients
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientInfoDTO> getPatients() {
        return patientInfoService.getPatients();
    }

    // get patients by name
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientInfoDTO> getPatientsByName(@RequestParam String fName, @RequestParam String lName) {
        return patientInfoService.getPatientsByName(fName, lName);
    }

    // get patient by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientInfoDTO getPatientById(@PathVariable UUID id) {
        return patientInfoService.getPatient(id);
    }

    // update patient
    @PostMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public PatientInfoDTO updatePatient(@RequestBody PatientInfoDTO patientInfoDTO, @PathVariable UUID id) {
        return patientInfoService.updatePatient(id, patientInfoDTO);
    }

    // get/add allergy
    @GetMapping("/{id}/allergy")
    public List<Allergy> getMedRecords(@PathVariable UUID id) {
        return medicalRecordService.getAllergiesByPatientId(id);
    }

    @PostMapping("/{id}/allergy")
    public Allergy addAllergy(@PathVariable UUID id, @RequestBody Allergy allergy) {
        return medicalRecordService.addAllergy(id, allergy);
    }

    // get/add hospitalization
    @GetMapping("/{id}/hospitalization")
    public List<Hospitalization> getHospitalizations(@PathVariable UUID id) {
        return medicalRecordService.getHospitalizationsByPatientId(id);
    }

    @PostMapping("/{id}/hospitalization")
    public Hospitalization addHospitalization(@PathVariable UUID id, @RequestBody Hospitalization hospitalization) {
        return medicalRecordService.addHospitalization(id, hospitalization);
    }

    // get/add surgery
    @GetMapping("/{id}/surgery")
    public List<Surgery> getSurgeries(@PathVariable UUID id) {
        return medicalRecordService.getSurgeriesByPatientId(id);
    }

    @PostMapping("/{id}/surgery")
    public Surgery addSurgery(@PathVariable UUID id, @RequestBody Surgery surgery) {
        return medicalRecordService.addSurgery(id, surgery);
    }

    // get/add consultation visit
    @GetMapping("/{id}/consultation")
    public List<ConsultationVisit> getConsultationVisits(@PathVariable UUID id) {
        return medicalRecordService.getConsultationVisitsByPatientId(id);
    }

    @PostMapping("/{id}/consultation")
    public ConsultationVisit addConsultationVisit(@PathVariable UUID id, @RequestBody ConsultationVisit consultationVisit) {
        return medicalRecordService.addConsultationVisit(id, consultationVisit);
    }

    // get lab results
    @GetMapping("/{id}/test")
    public List<LabResult> getLabResults(@PathVariable UUID id) {
        return medicalRecordService.getLabResultsByPatientId(id);
    }

    // get lab result details
    @GetMapping("/{id}/test/{testId}")
    public LabResult getLabResult(@PathVariable UUID id, @PathVariable UUID testId) {
        return labResultService.getLabResultDetail(testId);
    }

}
