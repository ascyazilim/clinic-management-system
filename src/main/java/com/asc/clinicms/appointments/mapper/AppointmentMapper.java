package com.asc.clinicms.appointments.mapper;

import com.asc.clinicms.appointments.dto.AppointmentResponse;
import com.asc.clinicms.appointments.entity.Appointment;

public class AppointmentMapper {
    public AppointmentMapper() {
    }

    public static AppointmentResponse toResponse(Appointment a){
        var patient = a.getPatient();
        var doctor = a.getDoctor();

        return new AppointmentResponse(
                a.getId(),
                patient.getId(),
                patient.getFirstName() + " " + patient.getLastName(),
                doctor.getId(),
                doctor.getFirstName() + " " + doctor.getLastName(),
                doctor.getBranch(),
                a.getStartAt(),
                a.getEndAt(),
                a.getStatus()
        );
    }
}
