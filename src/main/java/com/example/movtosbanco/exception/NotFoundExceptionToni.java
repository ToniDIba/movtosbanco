package com.example.movtosbanco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)


//Cumplimentar la clase ...ExceptionHandler
public class NotFoundExceptionToni extends RuntimeException {

    public NotFoundExceptionToni(String message) {
        super(message);
    }

}