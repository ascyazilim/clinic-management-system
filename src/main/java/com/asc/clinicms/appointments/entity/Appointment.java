package com.asc.clinicms.appointments.entity;

import com.asc.clinicms.common.entity.BaseEntity;
import com.asc.clinicms.doctors.entity.Doctor;
import com.asc.clinicms.patients.entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "appointments")
@SQLRestriction("deleted_at is null")
public class Appointment extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
}
