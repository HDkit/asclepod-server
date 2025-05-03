package com.hms.microservices.patient_service.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.microservices.patient_service.common.enums.ContactType;
import com.hms.microservices.patient_service.dto.PatientInfoDTO;
import com.hms.microservices.patient_service.models.ContactInfo;
import com.hms.microservices.patient_service.models.Patient;
import com.hms.microservices.patient_service.repositories.ContactInfoRepo;
import com.hms.microservices.patient_service.repositories.PatientRepo;

@Service
public class PatientInfoService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    ContactInfoRepo contactInfoRepo;

    public PatientInfoDTO registerPatient(PatientInfoDTO patientInfoDTO) {
        Patient patient = new Patient();
        patient.setFirstName(patientInfoDTO.getFirstName());
        patient.setLastName(patientInfoDTO.getLastName());
        patient.setBirthDate(patientInfoDTO.getBirthdate());
        patient.setSex(patientInfoDTO.getSex());
        patient.setWeight(patientInfoDTO.getWeight());
        patient.setHeight(patientInfoDTO.getHeight());
        patient.setInsCode(patientInfoDTO.getInsCode());
        patient.setDeleted(false);
        patient = patientRepo.save(patient);

        for (String phoneNumber : patientInfoDTO.getPhoneNumbers()) {
            ContactInfo phoneInfo = new ContactInfo();
            phoneInfo.setPatient(patient);
            phoneInfo.setContactType(ContactType.PHONE);
            phoneInfo.setContactVal(phoneNumber);
            contactInfoRepo.save(phoneInfo);
        }

        for (String mailAddr : patientInfoDTO.getMailAddrs()) {
            ContactInfo mailInfo = new ContactInfo();
            mailInfo.setPatient(patient);
            mailInfo.setContactType(ContactType.MAIL);
            mailInfo.setContactVal(mailAddr);
            contactInfoRepo.save(mailInfo);
        }

        return patientInfoDTO;
    }

    // only mark the deleted field as true
    public PatientInfoDTO deletePatient(UUID id) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);

        optionalPatient.ifPresent(patient -> {
            patient.setDeleted(true);
            patientRepo.save(patient);
        });
        return getPatient(id);
    }

    // basic info
    public PatientInfoDTO getPatient(UUID id) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);

        PatientInfoDTO patientInfoDTO = new PatientInfoDTO();

        optionalPatient.ifPresent(patient -> {
            patientInfoDTO.setId(patient.getId());
            patientInfoDTO.setFirstName(patient.getFirstName());
            patientInfoDTO.setLastName(patient.getLastName());
            patientInfoDTO.setBirthdate(patient.getBirthDate());
            patientInfoDTO.setSex(patient.getSex());
            patientInfoDTO.setWeight(patient.getWeight());
            patientInfoDTO.setHeight(patient.getHeight());
            patientInfoDTO.setInsCode(patient.getInsCode());
            patientInfoDTO.setDeleted(patient.getDeleted());

            List<ContactInfo> contactInfos = contactInfoRepo.findByPatient(patient);
            String[] phoneNumbers = contactInfos.stream()
                    .filter(contactInfo -> (contactInfo.getContactType() == ContactType.PHONE))
                    .map(ContactInfo::getContactVal)
                    .toArray(String[]::new);

            String[] mailAddrs = contactInfos.stream()
                    .filter(contactInfo -> (contactInfo.getContactType() == ContactType.MAIL))
                    .map(ContactInfo::getContactVal)
                    .toArray(String[]::new);

            patientInfoDTO.setPhoneNumbers(phoneNumbers);
            patientInfoDTO.setMailAddrs(mailAddrs);
        });
        return optionalPatient.isPresent() ? patientInfoDTO : null;
    }

    public List<PatientInfoDTO> getPatients() {
        List<Patient> patients = patientRepo.findAll();

        List<PatientInfoDTO> patientInfoDTOs = patients.stream()
                .map(patient -> {
                    PatientInfoDTO patientInfoDTO = new PatientInfoDTO();
                    patientInfoDTO.setId(patient.getId());
                    patientInfoDTO.setFirstName(patient.getFirstName());
                    patientInfoDTO.setLastName(patient.getLastName());
                    patientInfoDTO.setBirthdate(patient.getBirthDate());
                    patientInfoDTO.setSex(patient.getSex());
                    patientInfoDTO.setWeight(patient.getWeight());
                    patientInfoDTO.setHeight(patient.getHeight());
                    patientInfoDTO.setInsCode(patient.getInsCode());
                    patientInfoDTO.setDeleted(patient.getDeleted());

                    List<ContactInfo> contactInfos = contactInfoRepo.findByPatient(patient);
                    String[] phoneNumbers = contactInfos.stream()
                            .filter(contactInfo -> (contactInfo.getContactType() == ContactType.PHONE))
                            .map(ContactInfo::getContactVal)
                            .toArray(String[]::new);
                    String[] mailAddrs = contactInfos.stream()
                            .filter(contactInfo -> (contactInfo.getContactType() == ContactType.MAIL))
                            .map(ContactInfo::getContactVal)
                            .toArray(String[]::new);
                    patientInfoDTO.setPhoneNumbers(phoneNumbers);
                    patientInfoDTO.setMailAddrs(mailAddrs);

                    return patientInfoDTO;
                }).toList();

        return patientInfoDTOs;
    }

    public List<PatientInfoDTO> getPatientsByName(String firstName, String lastName) {
        List<Patient> patients = patientRepo.findByFirstNameAndLastName(firstName, lastName);
        List<PatientInfoDTO> patientInfoDTOs = patients.stream()
                .map(patient -> {
                    PatientInfoDTO patientInfoDTO = new PatientInfoDTO();
                    patientInfoDTO.setId(patient.getId());
                    patientInfoDTO.setFirstName(patient.getFirstName());
                    patientInfoDTO.setLastName(patient.getLastName());
                    patientInfoDTO.setBirthdate(patient.getBirthDate());
                    patientInfoDTO.setSex(patient.getSex());
                    patientInfoDTO.setWeight(patient.getWeight());
                    patientInfoDTO.setHeight(patient.getHeight());
                    patientInfoDTO.setInsCode(patient.getInsCode());
                    patientInfoDTO.setDeleted(patient.getDeleted());

                    List<ContactInfo> contactInfos = contactInfoRepo.findByPatient(patient);
                    String[] phoneNumbers = contactInfos.stream()
                            .filter(contactInfo -> (contactInfo.getContactType() == ContactType.PHONE))
                            .map(ContactInfo::getContactVal)
                            .toArray(String[]::new);
                    String[] mailAddrs = contactInfos.stream()
                            .filter(contactInfo -> (contactInfo.getContactType() == ContactType.MAIL))
                            .map(ContactInfo::getContactVal)
                            .toArray(String[]::new);
                    patientInfoDTO.setPhoneNumbers(phoneNumbers);
                    patientInfoDTO.setMailAddrs(mailAddrs);

                    return patientInfoDTO;
                }).toList();

        return patientInfoDTOs;
    }

    public PatientInfoDTO updatePatient(UUID id, PatientInfoDTO patientInfoDTO) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);
        optionalPatient.ifPresent(patient -> {
            patient.setFirstName(patientInfoDTO.getFirstName());
            patient.setLastName(patientInfoDTO.getLastName());
            patient.setBirthDate(patientInfoDTO.getBirthdate());
            patient.setSex(patientInfoDTO.getSex());
            patient.setWeight(patientInfoDTO.getWeight());
            patient.setHeight(patientInfoDTO.getHeight());
            patient.setInsCode(patientInfoDTO.getInsCode());
            patientRepo.save(patient);
        });

        return patientInfoDTO;
    }

}
