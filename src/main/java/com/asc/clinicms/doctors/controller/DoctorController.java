package com.asc.clinicms.doctors.controller;

import com.asc.clinicms.common.response.ApiResponse;
import com.asc.clinicms.doctors.dto.CreateDoctorRequest;
import com.asc.clinicms.doctors.dto.DoctorResponse;
import com.asc.clinicms.doctors.dto.UpdateDoctorRequest;
import com.asc.clinicms.doctors.mapper.DoctorMapper;
import com.asc.clinicms.doctors.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ApiResponse<DoctorResponse> create(@Valid @RequestBody CreateDoctorRequest req){
        return ApiResponse.success(DoctorMapper.toResponse(doctorService.create(req)));
    }

    @PutMapping("/{id}")
    public ApiResponse<DoctorResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateDoctorRequest req){
        return ApiResponse.success(DoctorMapper.toResponse(doctorService.update(id, req)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id){
        doctorService.delete(id);
        return ApiResponse.success(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<DoctorResponse> get(@PathVariable Long id) {
        return ApiResponse.success(DoctorMapper.toResponse(doctorService.get(id)));
    }

    @GetMapping
    public ApiResponse<Page<DoctorResponse>> list(@RequestParam(required = false) String search, Pageable pageable){
        var page = doctorService.list(search, pageable).map(DoctorMapper::toResponse);
        return ApiResponse.success(page);
    }
}
