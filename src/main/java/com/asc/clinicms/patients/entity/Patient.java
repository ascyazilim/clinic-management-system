package com.asc.clinicms.patients.entity;

import com.asc.clinicms.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {

    @Column(nullable = false, unique = true, length = 11)
    private String tcNo;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phone;

    private LocalDate birthDate;

    private String gender;
}
