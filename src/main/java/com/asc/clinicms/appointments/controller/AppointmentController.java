package com.asc.clinicms.appointments.controller;

import com.asc.clinicms.appointments.dto.AppointmentResponse;
import com.asc.clinicms.appointments.dto.CreateAppointmentRequest;
import com.asc.clinicms.appointments.dto.UpdateAppointmentRequest;
import com.asc.clinicms.appointments.mapper.AppointmentMapper;
import com.asc.clinicms.appointments.service.AppointmentService;
import com.asc.clinicms.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ApiResponse<AppointmentResponse> create(@Valid @RequestBody CreateAppointmentRequest req){
        return ApiResponse.success(AppointmentMapper.toResponse(appointmentService.create(req)));
    }

    @PutMapping("/{id}")
    public ApiResponse<AppointmentResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateAppointmentRequest req){
        return ApiResponse.success(AppointmentMapper.toResponse(appointmentService.update(id, req)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ApiResponse.success(null);
    }

    @GetMapping
    public ApiResponse<Page<AppointmentResponse>> list(
            @RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            Pageable pageable
            ){
        var page = appointmentService.list(doctorId, patientId, from, to, pageable)
                .map(AppointmentMapper::toResponse);
        return ApiResponse.success(page);
    }
}
