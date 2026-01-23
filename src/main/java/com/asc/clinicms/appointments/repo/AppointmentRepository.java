package com.asc.clinicms.appointments.repo;

import com.asc.clinicms.appointments.entity.Appointment;
import com.asc.clinicms.appointments.entity.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @EntityGraph(attributePaths = {"patient", "doctor"})
    Page<Appointment> findByDoctorId(Long doctorId, Pageable pageable);

    @EntityGraph(attributePaths = {"patient", "doctor"})
    Page<Appointment> findByDoctorIdAndStartAtGreaterThanEqualAndEndAtLessThanEqual(
            Long doctorId, LocalDateTime from, LocalDateTime to, Pageable pageable
    );

    @EntityGraph(attributePaths = {"patient", "doctor"})
    Page<Appointment> findByPatientId(Long patientId, Pageable pageable);

    @EntityGraph(attributePaths = {"patient", "doctor"})
    Page<Appointment> findByPatientIdAndStartAtGreaterThanEqualAndEndAtLessThanEqual(
            Long patientId, LocalDateTime from, LocalDateTime to, Pageable pageable
    );

    @EntityGraph(attributePaths = {"patient", "doctor"})
    Page<Appointment> findByDoctorIdAndPatientId(Long doctorId, Long patientId, Pageable pageable);

    @EntityGraph(attributePaths = {"patient", "doctor"})
    Page<Appointment> findByDoctorIdAndPatientIdAndStartAtGreaterThanEqualAndEndAtLessThanEqual(
            Long doctorId, Long patientId, LocalDateTime from, LocalDateTime to, Pageable pageable
    );

    /*
    @Query("""
            select a from Appointment a
            join fetch a.patient
            join fetch a.doctor
            where a.doctor.id = :doctorId
            """)
    Page<Appointment> findByDoctorIdFetch(Long doctorId, Pageable pageable);

     */

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


}
