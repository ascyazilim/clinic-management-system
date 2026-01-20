package com.asc.clinicms.appointments.repo;

import com.asc.clinicms.appointments.entity.Appointment;
import com.asc.clinicms.appointments.entity.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("""
            select count(a) > 0 from Appointment a 
            where a.doctor.id = :doctorId
              and a.status = :status
              and :startAt < a.endAt
              and :endAt > a.startAt
            """)
    boolean existsOverlapForDoctor(Long doctorId, AppointmentStatus status, LocalDateTime startAt, LocalDateTime endAt);

    @Query("""
            select count(a) > 0 from Appointment a
            where a.doctor.id = :doctorId
              and a.status = :status
              and a.id <> :appointmentId
              and :startAt < a.endAt
              and :endAt > a.startAt
            """)
    boolean existsOverlapForDoctorExcludingId(Long doctorId, Long appointmentId, AppointmentStatus status, LocalDateTime startAt, LocalDateTime endAt);

    @Query("""
            select a from Appointment a
            where (:doctorId is null or a.doctor.id = :doctorId)
              and (:patientId is null or a.patient.id = :patientId)
              and (:from is null or a.startAt >= :from)
              and (:to is null or a.endAt <= :to)
            """)
    Page<Appointment> filter(Long doctorId, Long patientId, LocalDateTime from, LocalDateTime to, Pageable pageable);

}
