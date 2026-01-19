package com.asc.clinicms.patients.service;

import com.asc.clinicms.common.exception.BusinessException;
import com.asc.clinicms.common.exception.ErrorCode;
import com.asc.clinicms.patients.dto.CreatePatientRequest;
import com.asc.clinicms.patients.dto.UpdatePatientRequest;
import com.asc.clinicms.patients.entity.Patient;
import com.asc.clinicms.patients.repo.PatientRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient create(CreatePatientRequest req) {
        patientRepository.findByTcNo(req.getTcNo())
                .ifPresent(p -> { throw new BusinessException(ErrorCode.CONFLICT, "Patient already exists with tcNo: " + req.getTcNo()); });
        Patient p = new Patient();
        p.setTcNo(req.getTcNo());
        p.setFirstName(req.getFirstName());
        p.setLastName(req.getLastName());
        p.setPhone(req.getPhone());

        return patientRepository.save(p);
    }

    public Page<Patient> list(String search, Pageable pageable) {
        if (search == null || search.isBlank()) {
            return patientRepository.findAll(pageable);
        }
        return patientRepository.search(search.trim(), pageable);
    }

    public Patient get(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "Patient not found: " + id));
    }

    public Patient update(Long id, UpdatePatientRequest req){
        Patient p  = get(id);

        patientRepository.findByTcNo(req.tcNo())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(x -> {throw new BusinessException(ErrorCode.CONFLICT, "Patient already exist with tcNo: " + req.tcNo());});
        p.setTcNo(req.tcNo());
        p.setFirstName(req.firstName());
        p.setLastName(req.lastName());
        p.setPhone(req.phone());
        p.setBirthDate(req.birthDate());
        p.setGender(req.gender());

        return patientRepository.save(p);
    }

    public void delete(Long id){
        Patient p = get(id);
        p.setDeletedAt(LocalDateTime.now());
        patientRepository.delete(p);
    }

    public Patient getByTcNo(String tcNo) {
        return patientRepository.findByTcNo(tcNo)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "Patient not found with tcNo: " + tcNo));
    }


}
