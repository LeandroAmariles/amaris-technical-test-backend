package com.amaris.technicaltest.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
@Slf4j
public class GlobalhandlerException  extends Exception {


    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorDetails> handleClientException(ClientException e) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .details(e.getLocalizedMessage())
                .message("Error occurred while processing request")
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorDetails> handleTimeoutException(TimeoutException e) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .details(e.getLocalizedMessage())
                .message("Time out client")
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
