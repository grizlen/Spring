package ru.geekbrains.SpringSecurityapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(
                String.format(
                        "<p>Exception: %s<br> HttpStatus: %d<br>message: %s",
                        e.getClass().getName(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
