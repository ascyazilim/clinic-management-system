package com.asc.clinicms.patients.mapper;

import com.asc.clinicms.patients.dto.PatientResponse;
import com.asc.clinicms.patients.entity.Patient;

public class PatientMapper {
    private PatientMapper() {}

    public static PatientResponse toResponse(Patient p) {
        return new PatientResponse(
                p.getId(),
                p.getTcNo(),
                p.getFirstName(),
                p.getLastName(),
                p.getPhone(),
                p.getBirthDate(),
                p.getGender()
        );
    }
}
