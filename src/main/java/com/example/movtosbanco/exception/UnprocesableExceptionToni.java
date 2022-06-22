package com.example.movtosbanco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

//Cumplimentar la clase ...ExceptionHandler
public class UnprocesableExceptionToni extends RuntimeException {

    public UnprocesableExceptionToni(String message) {
        super(message);
    }

}
