package com.practo.exception;

import com.practo.dictionary.APIErrorCode;
import com.practo.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleDuplicateEntityException(DuplicateEntryException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AttendanceNotFoundException.class)
    public ResponseEntity<String> AttendanceNotFoundException(AttendanceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(InspireException.class)
//    public ResponseEntity<String> handleInspireException(InspireException e)
//    {
//        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//    }
    @ExceptionHandler(InspireException.class)
    public ResponseEntity<Object> handleInspireException(InspireException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("statusCode", ex.getErrorCode());
    errorResponse.put("errorMessage", ex.getErrorMessage());
    return new ResponseEntity<>(errorResponse,HttpStatus.OK);
}

}
