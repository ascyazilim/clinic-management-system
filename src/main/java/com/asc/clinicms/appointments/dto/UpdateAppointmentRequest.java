package com.asc.clinicms.appointments.dto;

import com.asc.clinicms.appointments.entity.AppointmentStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateAppointmentRequest(
        @NotNull(message = "startAt is required")
        LocalDateTime startAt,

        @NotNull(message = "endAt is required")
        LocalDateTime endAt,

        @NotNull(message = "status is required")
        AppointmentStatus status
) {
}
