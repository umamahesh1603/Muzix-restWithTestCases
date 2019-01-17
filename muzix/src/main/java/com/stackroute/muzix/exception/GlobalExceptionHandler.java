package com.stackroute.muzix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(TrackAlreadyExistsException.class)
        public ResponseEntity<ErrorMessage> resourceNotFoundException(TrackAlreadyExistsException ex) {
            ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(),"Can't save");
            return new ResponseEntity<ErrorMessage>(errorDetails, HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(TrackNotFoundException.class)
        public ResponseEntity<ErrorMessage> resourceNotFoundException(TrackNotFoundException ex) {
            ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(),"Bad Request");
            return new ResponseEntity<ErrorMessage>(errorDetails, HttpStatus.NOT_FOUND);
        }



    }

