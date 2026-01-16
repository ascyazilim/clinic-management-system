package com.asc.clinicms.patients.repo;

import com.asc.clinicms.patients.entity.Patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByTcNo(String tcNo);

    Page<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable
    );

    @Query("""
       select p from Patient p
       where lower(p.firstName) like lower(concat('%', :q, '%'))
          or lower(p.lastName) like lower(concat('%', :q, '%'))
          or p.tcNo like concat('%', :q, '%')
       """)
    Page<Patient> search(@Param("q") String q, Pageable pageable);
}
