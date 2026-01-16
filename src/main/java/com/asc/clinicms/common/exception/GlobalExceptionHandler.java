package com.asc.clinicms.common.exception;

import com.asc.clinicms.common.response.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusiness(BusinessException ex) {
        return ApiResponse.failure(ex.getCode().name(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleAny(Exception ex) {
        return ApiResponse.failure(ErrorCode.INTERNAL_EROR.name(), ex.getMessage());
    }
}