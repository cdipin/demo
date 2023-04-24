package com.example.demo.utils;

import com.example.demo.model.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class, ValidationException.class, MethodArgumentNotValidException.class, HttpClientErrorException.MethodNotAllowed.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handle(Exception e) {
        log.warn("Exception occurred {}", e.getMessage());
        return ResponseEntity.badRequest().body(getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorResponse> handle(RuntimeException e) {
        log.warn("Exception occurred {}", e.getMessage());
        return ResponseEntity.internalServerError().body(getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e));
    }

    private ErrorResponse getErrorResponse(HttpStatusCode statusCode, Exception e) {
        return new ErrorResponse(statusCode, e.getMessage());
    }
}