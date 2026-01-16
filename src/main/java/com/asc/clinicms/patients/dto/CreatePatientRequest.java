package com.asc.clinicms.patients.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePatientRequest {

    @NotBlank(message = "tcNo is required")
    @Pattern(regexp = "\\d{11}", message = "tcNo must be 11 digits")
    private String tcNo;

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastname is required")
    private String lastName;

    private String phone;
}
