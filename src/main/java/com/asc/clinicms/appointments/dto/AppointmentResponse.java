package com.asc.clinicms.appointments.dto;

import com.asc.clinicms.appointments.entity.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long patientId,
        String patientFullName,
        Long doctorId,
        String doctorFullName,
        String doctorBranch,
        LocalDateTime startAt,
        LocalDateTime endAt,
        AppointmentStatus status
) {
}
