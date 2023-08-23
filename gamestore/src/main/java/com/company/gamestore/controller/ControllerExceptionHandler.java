package com.company.gamestore.controller;

import com.company.gamestore.model.CustomErrorResponse;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleGenericNotFound(IllegalArgumentException e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                e.getMessage());
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(LocalDateTime.now());

        //Wrap in a response entity
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error,
                HttpStatus.UNPROCESSABLE_ENTITY);

        return responseEntity;
    }
}