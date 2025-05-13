package com.hms.microservices.patient_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hms.microservices.patient_service.common.dto.MedicalInfoRequestDTO;
import com.hms.microservices.patient_service.common.dto.MedicalInfoResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.AllergyResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.ConsultationResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.HospitalizationResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.SurgeryResponseDTO;
import com.hms.microservices.patient_service.common.dto.medRecs.testRes.LabResResponseDTO;
import com.hms.microservices.patient_service.common.response.Response;
import com.hms.microservices.patient_service.services.MedicalRecordService.MedicalRecordService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/patients/{pid}")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    private UUID pid;

    @ModelAttribute
    public void setPatientId(@PathVariable UUID pid) {
        this.pid = pid;
    }

    // get medical records by type (different endpoints for each type)
    @GetMapping("/allergy")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<AllergyResponseDTO>> getAllergy() {
        return new Response<List<AllergyResponseDTO>>(true, "", medicalRecordService.getAllergy(pid));
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<LabResResponseDTO>> getTestResponse() {
        return new Response<List<LabResResponseDTO>>(true, "", medicalRecordService.getLabResult(pid));
    }

    @GetMapping("/consult")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<ConsultationResponseDTO>> getConsultResponse() {
        return new Response<List<ConsultationResponseDTO>>(true, "", medicalRecordService.getConsultation(pid));
    }

    @GetMapping("/hospitalization")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<HospitalizationResponseDTO>> getHospitalization() {
        return new Response<List<HospitalizationResponseDTO>>(true, "", medicalRecordService.getHospitalization(pid));
    }

    @GetMapping("/surgery")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<SurgeryResponseDTO>> getSurgery() {
        return new Response<List<SurgeryResponseDTO>>(true, "", medicalRecordService.getSurgery(pid));
    }

    // put medical records (a single endpoint for all types)
    @PostMapping("/records")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<MedicalInfoResponseDTO> addMedicalRecord(@RequestBody MedicalInfoRequestDTO medicalInfo) {
        return new Response<MedicalInfoResponseDTO>(true, "",
                medicalRecordService.putMedicalInfo(pid, medicalInfo));
    }
}
