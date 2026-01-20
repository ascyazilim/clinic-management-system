package com.asc.clinicms.appointments.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateAppointmentRequest(
        @NotNull(message = "patientId is required")
        Long patientId,

        @NotNull(message = "doctorId is required")
        Long doctorId,

        @NotNull(message = "startAt is required")
        LocalDateTime startAt,

        @NotNull(message = "endAt is required")
        LocalDateTime endAt
) { }
