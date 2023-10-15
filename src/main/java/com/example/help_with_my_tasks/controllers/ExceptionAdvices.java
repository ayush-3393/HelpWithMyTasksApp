package com.example.help_with_my_tasks.controllers;

import com.example.help_with_my_tasks.dtos.ErrorMessageDto;
import com.example.help_with_my_tasks.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(Exception exception){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.NOT_FOUND);
    }
}
