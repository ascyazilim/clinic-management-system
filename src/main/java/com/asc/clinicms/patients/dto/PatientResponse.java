package com.asc.clinicms.patients.dto;

import java.time.LocalDate;

public record PatientResponse (

    Long id,
    String tcNo,
    String firstName,
    String lastName,
    String phone,
    LocalDate birthDate,
    String gender
){}
