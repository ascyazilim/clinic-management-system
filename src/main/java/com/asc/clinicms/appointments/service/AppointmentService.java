package com.asc.clinicms.appointments.service;

import com.asc.clinicms.appointments.dto.CreateAppointmentRequest;
import com.asc.clinicms.appointments.dto.UpdateAppointmentRequest;
import com.asc.clinicms.appointments.entity.Appointment;
import com.asc.clinicms.appointments.entity.AppointmentStatus;
import com.asc.clinicms.appointments.repo.AppointmentRepository;
import com.asc.clinicms.common.exception.BusinessException;
import com.asc.clinicms.common.exception.ErrorCode;
import com.asc.clinicms.doctors.service.DoctorService;
import com.asc.clinicms.patients.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public Appointment create(CreateAppointmentRequest req){
        validateTimeRange(req.startAt(), req.endAt());

        var patient = patientService.get(req.patientId());
        var doctor = doctorService.get(req.doctorId());

        boolean overlap = appointmentRepository.existsOverlapForDoctor(
                doctor.getId(),
                AppointmentStatus.SCHEDULED,
                req.startAt(),
                req.endAt()
        );
        if (overlap) {
            throw new BusinessException(ErrorCode.CONFLICT, "Doctor already has an appointment in this time range.");
        }

        Appointment a = new Appointment();
        a.setPatient(patient);
        a.setDoctor(doctor);
        a.setStartAt(req.startAt());
        a.setEndAt(req.endAt());
        a.setStatus(AppointmentStatus.SCHEDULED);

        return appointmentRepository.save(a);
    }

    public Appointment update(Long id, UpdateAppointmentRequest req) {
        validateTimeRange(req.startAt(), req.endAt());

        Appointment a = get(id);

        boolean overlap = appointmentRepository.existsOverlapForDoctorExcludingId(
                a.getDoctor().getId(),
                a.getId(),
                AppointmentStatus.SCHEDULED,
                req.startAt(),
                req.endAt()
        );

        if (overlap) {
            throw new BusinessException(ErrorCode.CONFLICT, "Doctor already has an appointment in this time range.");
        }

        a.setStartAt(req.startAt());
        a.setEndAt(req.endAt());
        a.setStatus(req.status());

        return appointmentRepository.save(a);
    }

    public void delete(Long id){
        Appointment a = get(id);
        a.setDeletedAt(LocalDateTime.now());
        appointmentRepository.save(a);
    }
    public Appointment get(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "Appointment not found: " +id));
    }

    public Page<Appointment> list(Long doctorId, Long patientId, LocalDateTime from, LocalDateTime to, Pageable pageable){
        return appointmentRepository.filter(doctorId, patientId, from, to, pageable);
    }
    private void validateTimeRange(LocalDateTime startAt, LocalDateTime endAt){
        if (startAt == null || endAt == null) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "startAt and endAt are required");
        }
        if (!endAt.isAfter(startAt)) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "endAt must be after startAt");
        }

        if (startAt.plusHours(4).isBefore(endAt)){
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Appointment duration is too long");
        }
    }
}
