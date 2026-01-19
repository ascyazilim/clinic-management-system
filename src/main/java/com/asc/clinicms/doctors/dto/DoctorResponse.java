package com.asc.clinicms.doctors.dto;

public record DoctorResponse(
        Long id,
        String firstName,
        String lastName,
        String branch,
        String phone
) {
}
