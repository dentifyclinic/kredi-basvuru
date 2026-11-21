package com.murat.kredibasvuru.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail getException(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");
        Map<String, Object> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });
        problemDetail.setProperties(fieldErrors);
        return problemDetail;
    }
}
