package com.practice.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.practice.demo.dto.ApiError;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex){
    	
		ApiError error = new ApiError(
				                      HttpStatus.NOT_FOUND.value(),
				                      "NOT_FOUND",
				                      ex.getMessage()
				                      );
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex){
		ApiError error = new ApiError(
				                     HttpStatus.BAD_REQUEST.value(),
				                     "BAD_REQUEST",
				                     ex.getMessage()
				                     );
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex){
		
		String message = ex.getBindingResult()
				           .getFieldErrors()
				           .stream()
				           .map(e -> e.getField() + ":" + e.getDefaultMessage())
				           .findFirst()
				           .orElse("Validation failed");
		
		ApiError error = new ApiError(
                                     HttpStatus.BAD_REQUEST.value(),
                                     "VALIDATION_ERROR",
                                     message
				                      );
		
		return ResponseEntity.badRequest().body(error);
		
	}
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        // log.error("Unhandled exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(500, "INTERNAL_SERVER_ERROR",
                        "An unexpected error occurred"));
    }
	
}
