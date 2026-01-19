package com.asc.clinicms.doctors.service;

import com.asc.clinicms.common.exception.BusinessException;
import com.asc.clinicms.common.exception.ErrorCode;
import com.asc.clinicms.doctors.dto.CreateDoctorRequest;
import com.asc.clinicms.doctors.dto.UpdateDoctorRequest;
import com.asc.clinicms.doctors.entity.Doctor;
import com.asc.clinicms.doctors.repo.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public Doctor create(CreateDoctorRequest req) {
        Doctor d = new Doctor();
        d.setFirstName(req.firstName());
        d.setLastName(req.lastName());
        d.setBranch(req.branch());
        d.setPhone(req.phone());
        return doctorRepository.save(d);
    }

    public Doctor update(Long id, UpdateDoctorRequest req){
        Doctor d = get(id);
        d.setFirstName(req.firstName());
        d.setLastName(req.lastName());
        d.setBranch(req.branch());
        d.setPhone(req.phone());
        return doctorRepository.save(d);
    }

    public void delete(Long id){
        Doctor d = get(id);
        d.setDeletedAt(LocalDateTime.now());
        doctorRepository.save(d);
    }

    public Doctor get(Long id){
        return doctorRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "Doctor not found: " +id));
    }

    public Page<Doctor> list(String search, Pageable pageable){
        if (search == null || search.isBlank()) {
            return doctorRepository.findAll(pageable);
        }
        return doctorRepository.search(search.trim(), pageable);
    }
}
