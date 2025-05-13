package com.hms.microservices.patient_service.controllers;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hms.microservices.patient_service.common.errors.PatientNotFoundError;
import com.hms.microservices.patient_service.common.response.Response;

// Revise this later
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<Response<Void>> handleHibernateException(HibernateException e) {
        Response<Void> response = new Response<Void>(false, "Database error: " + e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(PatientNotFoundError.class)
    public ResponseEntity<Response<Void>> handlePatientNotFoundError(PatientNotFoundError e) {
        Response<Void> response = new Response<Void>(false, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Response<Void> response = new Response<Void>(false, "Invalid request body: " + e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
