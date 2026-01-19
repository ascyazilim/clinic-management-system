package com.asc.clinicms.doctors.mapper;

import com.asc.clinicms.doctors.dto.DoctorResponse;
import com.asc.clinicms.doctors.entity.Doctor;

public class DoctorMapper {
    private DoctorMapper() {}

    public static DoctorResponse toResponse(Doctor d){
        return new DoctorResponse(
                d.getId(),
                d.getFirstName(),
                d.getLastName(),
                d.getBranch(),
                d.getPhone()
        );
    }
}
