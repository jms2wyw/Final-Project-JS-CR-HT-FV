package com.company.gamestore.controller;

import com.company.gamestore.model.CustomErrorResponse;
import com.sun.istack.NotNull;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<CustomErrorResponse>> recordValidationError(MethodArgumentNotValidException e) {
        // holds validation errors
        BindingResult result = e.getBindingResult();

        //get validation errors stored in FieldError Objects
        List<FieldError> fieldErrors = result.getFieldErrors();

        // turn fieldErrors into CustomErrorResponse
        List<CustomErrorResponse> listOfErrors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            CustomErrorResponse errorResponse =
                    new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), fieldError.getDefaultMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            listOfErrors.add(errorResponse);
        }

        // gather responses into one entity
        ResponseEntity<List<CustomErrorResponse>> responseEntity =
                new ResponseEntity<>(listOfErrors, HttpStatus.UNPROCESSABLE_ENTITY);

        return responseEntity;
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> recordNotFoundException(NotFoundException e) {
        CustomErrorResponse customErrorResponse =
                new CustomErrorResponse(HttpStatus.NOT_FOUND.toString(), "Item not found");
        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        // gather responses into one entity
        ResponseEntity<CustomErrorResponse> responseEntity =
                new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);

        return responseEntity;
    }
}