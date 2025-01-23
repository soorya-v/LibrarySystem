package com.onlineLibraryManagementSystem.webApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		String message = "Error: "+ ex.getMessage();
        return new ResponseEntity<>(message , HttpStatus.BAD_REQUEST);
    }
	
}
