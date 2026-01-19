package com.asc.clinicms.doctors.entity;

import com.asc.clinicms.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "doctors")
@SQLRestriction("deleted_at is null")
public class Doctor extends BaseEntity {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String branch; //Kardiyoloji, Dahiliye vs.

    private String phone;

}
