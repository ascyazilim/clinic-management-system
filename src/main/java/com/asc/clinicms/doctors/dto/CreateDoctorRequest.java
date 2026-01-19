package com.asc.clinicms.doctors.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDoctorRequest(
    @NotBlank(message = "firstName is required")
    String firstName,

    @NotBlank(message = "lastName is required")
    String lastName,

    @NotBlank(message = "branch is required")
    String branch,

    String phone
){}
