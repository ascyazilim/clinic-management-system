package com.asc.clinicms.patients.controller;

import com.asc.clinicms.common.response.ApiResponse;
import com.asc.clinicms.patients.dto.CreatePatientRequest;
import com.asc.clinicms.patients.dto.PatientResponse;
import com.asc.clinicms.patients.dto.UpdatePatientRequest;
import com.asc.clinicms.patients.entity.Patient;
import com.asc.clinicms.patients.mapper.PatientMapper;
import com.asc.clinicms.patients.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")

public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ApiResponse<PatientResponse> create(@Valid @RequestBody CreatePatientRequest req){
        var saved = patientService.create(req);
        return ApiResponse.success(PatientMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ApiResponse<PatientResponse> update(@PathVariable Long id, @Valid @RequestBody UpdatePatientRequest req){
        var updated = patientService.update(id, req);
        return ApiResponse.success(PatientMapper.toResponse(updated));
    }

    @GetMapping
    public ApiResponse<Page<PatientResponse>> list(@RequestParam(required = false) String search, Pageable pageable){
        var page = patientService.list(search,pageable)
                .map(PatientMapper::toResponse);
        return ApiResponse.success(page);
    }

    @GetMapping("/{id}")
    public ApiResponse<PatientResponse> get(@PathVariable Long id){
        var p = patientService.get(id);
        return ApiResponse.success(PatientMapper.toResponse(p));
    }

    @GetMapping("/by-tcno/{tcNo}")
    public ApiResponse<PatientResponse> getByTcNo(@PathVariable String tcNo){
        var p = patientService.getByTcNo(tcNo);
        return ApiResponse.success(PatientMapper.toResponse(p));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ApiResponse.success(null);
    }
}
