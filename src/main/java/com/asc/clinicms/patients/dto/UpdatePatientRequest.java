package com.asc.clinicms.patients.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdatePatientRequest(
        @NotBlank(message = "tcNo is required")
        String tcNo,

        @NotBlank(message = "firstName is required")
        String firstName,

        @NotBlank(message = "lastName is required")
        String lastName,

        String phone,
        LocalDate birthDate,
        String gender
) { }
