package com.hms.microservices.patient_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.microservices.patient_service.common.dto.PatientInfoRequestDTO;
import com.hms.microservices.patient_service.common.dto.PatientInfoResponseDTO;
import com.hms.microservices.patient_service.common.dto.mapper.PatientInfoResponseMapper;
import com.hms.microservices.patient_service.common.errors.PatientNotFoundError;
import com.hms.microservices.patient_service.models.ContactInfo;
import com.hms.microservices.patient_service.models.Patient;
import com.hms.microservices.patient_service.repositories.ContactInfoRepo;
import com.hms.microservices.patient_service.repositories.PatientRepo;

import jakarta.transaction.Transactional;

@Service
public class PatientInfoService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    ContactInfoRepo contactInfoRepo;

    @Autowired
    PatientInfoResponseMapper patientInfoResponseMapper;

    // create new patient
    @Transactional
    public PatientInfoResponseDTO registerPatient(PatientInfoRequestDTO patientInfoRequestDTO) {
        Patient patient = patientInfoResponseMapper.toPatient(patientInfoRequestDTO);
        patientRepo.save(patient);
        List<ContactInfo> contactInfos = patientInfoResponseMapper.toContactInfosList(
                patientInfoRequestDTO.getContacts(),
                patient);
        contactInfoRepo.saveAll(contactInfos);
        PatientInfoResponseDTO patientInfoDTO = patientInfoResponseMapper.toPatientInfoResponseDTO(patient,
                contactInfos);
        return patientInfoDTO;
    }

    // delete patient by id, only mark the deleted field as true
    public PatientInfoResponseDTO deletePatient(UUID id) throws PatientNotFoundError {
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundError(id));
        List<ContactInfo> contactInfos = contactInfoRepo.findByPatient_Id(id);
        patient.setDeleted(true);
        patientRepo.save(patient);
        return patientInfoResponseMapper.toPatientInfoResponseDTO(patient, contactInfos);
    }

    // get all patients
    public List<PatientInfoResponseDTO> getPatients() {
        List<Patient> patients = patientRepo.findAll();
        List<PatientInfoResponseDTO> patientInfoDTOs = new ArrayList<PatientInfoResponseDTO>();
        for (Patient patient : patients) {
            List<ContactInfo> contactInfos = contactInfoRepo.findByPatient_Id(patient.getId());
            patientInfoDTOs.add(patientInfoResponseMapper.toPatientInfoResponseDTO(patient, contactInfos));
        }
        return patientInfoDTOs;
    }

    // basic patient info by id
    public PatientInfoResponseDTO getPatient(UUID id) {
        Patient patient = patientRepo.findById(id).orElse(null);
        List<ContactInfo> contactInfos = contactInfoRepo.findByPatient_Id(id);
        return patientInfoResponseMapper.toPatientInfoResponseDTO(patient, contactInfos);
    }

    // get patients by firstName and lastName
    public List<PatientInfoResponseDTO> getPatientsByName(String firstName,
            String lastName) {
        List<Patient> patients = patientRepo.findByFirstNameAndLastName(firstName, lastName);
        List<PatientInfoResponseDTO> patientInfoDTOs = new ArrayList<PatientInfoResponseDTO>();
        for (Patient patient : patients) {
            List<ContactInfo> contactInfos = contactInfoRepo.findByPatient_Id(patient.getId());
            patientInfoDTOs.add(patientInfoResponseMapper.toPatientInfoResponseDTO(patient, contactInfos));
        }
        return patientInfoDTOs;
    }

    // update patient info by id
    @Transactional
    public PatientInfoResponseDTO updatePatient(UUID id, PatientInfoRequestDTO patientInfoRequestDTO)
            throws PatientNotFoundError {
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundError(id));
        patientInfoResponseMapper.updatePatientFromDTO(patientInfoRequestDTO, patient);
        patient.setId(id);
        patientRepo.save(patient);
        List<ContactInfo> newContactInfos = patientInfoResponseMapper.toContactInfosList(
                patientInfoRequestDTO.getContacts(),
                patient);
        contactInfoRepo.saveAll(newContactInfos);
        PatientInfoResponseDTO patientInfoDTO = patientInfoResponseMapper.toPatientInfoResponseDTO(patient,
                newContactInfos);
        return patientInfoDTO;
    }

}
