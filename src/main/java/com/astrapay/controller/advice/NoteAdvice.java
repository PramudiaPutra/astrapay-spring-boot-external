package com.astrapay.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class NoteAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "INVALID_PARAMETER");
        errorResponse.put("field", Objects.requireNonNull(fieldError).getField());
        errorResponse.put("message", fieldError.getDefaultMessage());
        errorResponse.put("timestamp", new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
