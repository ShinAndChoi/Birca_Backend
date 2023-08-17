package com.app.birca.controller;

import com.app.birca.dto.response.error.BindErrorResponse;
import com.app.birca.dto.response.error.ErrorResponse;
import com.app.birca.exception.BircaException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BircaException.class)
    public ResponseEntity<ErrorResponse> exception(BircaException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(statusCode)
                .body(body);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public BindErrorResponse BindException(BindException bindException) {
        List<FieldError> errors = bindException.getFieldErrors();

        return BindErrorResponse.builder()
                .message(errors.stream()
                        .map(e -> e.getDefaultMessage())
                        .collect(Collectors.toList()))
                .build();
    }

}
