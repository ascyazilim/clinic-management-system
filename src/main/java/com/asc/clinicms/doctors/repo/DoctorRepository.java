package com.asc.clinicms.doctors.repo;

import com.asc.clinicms.doctors.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("""
            select d from Doctor d 
            where lower(d.firstName) like lower(concat('%', :q, '%') ) 
                or lower(d.lastName) like lower(concat('%', :q, '%') ) 
                or lower(d.branch) like lower(concat('%', :q, '%') ) 
            """)
    Page<Doctor> search(String q, Pageable pageable);
}
