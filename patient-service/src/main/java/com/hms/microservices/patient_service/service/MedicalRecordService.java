package com.hms.microservices.patient_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.microservices.patient_service.models.Allergy;
import com.hms.microservices.patient_service.models.ConsultationVisit;
import com.hms.microservices.patient_service.models.Hospitalization;
import com.hms.microservices.patient_service.models.LabResult;
import com.hms.microservices.patient_service.models.Patient;
import com.hms.microservices.patient_service.models.Surgery;
import com.hms.microservices.patient_service.repositories.AllergyRepo;
import com.hms.microservices.patient_service.repositories.ConsultationVisitRepo;
import com.hms.microservices.patient_service.repositories.HospitalizationRepo;
import com.hms.microservices.patient_service.repositories.LabResultRepo;
import com.hms.microservices.patient_service.repositories.PatientRepo;
import com.hms.microservices.patient_service.repositories.SurgeryRepo;
import com.hms.microservices.patient_service.service.LabResultService.LabResultService;

@Service
public class MedicalRecordService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    AllergyRepo allergyRepo;

    @Autowired
    HospitalizationRepo hospitalizationRepo;

    @Autowired
    ConsultationVisitRepo consultationVisitRepo;

    @Autowired
    LabResultRepo labResultRepo;

    @Autowired
    SurgeryRepo surgeryRepo;

    @Autowired
    LabResultService labResultService;

    public List<Allergy> getAllergiesByPatientId(UUID patientID) {
        return allergyRepo.findByPatient_Id(patientID);
    }

    public List<Hospitalization> getHospitalizationsByPatientId(UUID patientID) {
        return hospitalizationRepo.findByPatient_Id(patientID);
    }

    public List<ConsultationVisit> getConsultationVisitsByPatientId(UUID patientID) {
        return consultationVisitRepo.findByPatient_Id(patientID);
    }

    public List<LabResult> getLabResultsByPatientId(UUID patientID) {
        return labResultRepo.findByPatient_Id(patientID);
    }

    public List<Surgery> getSurgeriesByPatientId(UUID patientID) {
        return surgeryRepo.findByPatient_Id(patientID);
    }

    public Allergy addAllergy(UUID patientId, Allergy allergy) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        allergy.setPatient(patient);
        return allergyRepo.save(allergy);
    }

    public Hospitalization addHospitalization(UUID patientId, Hospitalization hospitalization) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        hospitalization.setPatient(patient);
        return hospitalizationRepo.save(hospitalization);
    }

    public ConsultationVisit addConsultationVisit(UUID patientId, ConsultationVisit consultationVisit) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        consultationVisit.setPatient(patient);
        return consultationVisitRepo.save(consultationVisit);
    }

    public Surgery addSurgery(UUID patientId, Surgery surgery) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        surgery.setPatient(patient);
        return surgeryRepo.save(surgery);
    }
}
