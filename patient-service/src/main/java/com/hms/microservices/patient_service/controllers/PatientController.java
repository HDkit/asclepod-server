package com.hms.microservices.patient_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hms.microservices.patient_service.common.dto.PatientInfoRequestDTO;
import com.hms.microservices.patient_service.common.dto.PatientInfoResponseDTO;
import com.hms.microservices.patient_service.common.response.Response;
import com.hms.microservices.patient_service.services.PatientInfoService;

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

    // create new patient
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<PatientInfoResponseDTO> postNewPatient(@RequestBody PatientInfoRequestDTO patientInfoDTO) {
        return new Response<PatientInfoResponseDTO>(true, "", patientInfoService.registerPatient(patientInfoDTO));
    }

    // delete patient
    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public Response<PatientInfoResponseDTO> deletePatient(@PathVariable UUID id) {
        return new Response<PatientInfoResponseDTO>(true, "", patientInfoService.deletePatient(id));
    }

    // get all patients
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<PatientInfoResponseDTO>> getPatients() {
        return new Response<List<PatientInfoResponseDTO>>(true, "", patientInfoService.getPatients());
    }

    // get patients by name
    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<PatientInfoResponseDTO>> getPatientsByName(@RequestParam String fName,
            @RequestParam String lName) {
        return new Response<List<PatientInfoResponseDTO>>(true, "", patientInfoService.getPatientsByName(fName, lName));
    }

    // get patient by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<PatientInfoResponseDTO> getPatientById(@PathVariable UUID id) {
        return new Response<PatientInfoResponseDTO>(true, "", patientInfoService.getPatient(id));
    }

    // update patient
    @PostMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public Response<PatientInfoResponseDTO> updatePatient(@RequestBody PatientInfoRequestDTO patientInfoDTO,
            @PathVariable UUID id) {
        return new Response<PatientInfoResponseDTO>(true, "", patientInfoService.updatePatient(id, patientInfoDTO));
    }
}
