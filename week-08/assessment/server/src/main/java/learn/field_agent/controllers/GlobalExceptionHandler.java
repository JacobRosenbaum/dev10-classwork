package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import learn.field_agent.domain.AliasService;
import learn.field_agent.domain.ErrorResponseService;
import learn.field_agent.models.ErrorResponse;
import learn.field_agent.models.SecurityClearance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ErrorResponseService service;

    public GlobalExceptionHandler(ErrorResponseService service) {
        this.service = service;
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<String> handleException(BadSqlGrammarException ex) {
        ErrorResponse response = new ErrorResponse(Timestamp.valueOf(LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Bad SQL Grammar Exception",
                ex.getMessage()
                );
        String message = "An internal issue has occurred, request cannot be completed.";
        service.add(response);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleException(InvalidFormatException ex) {
        ErrorResponse response = new ErrorResponse(Timestamp.valueOf(LocalDateTime.now()),
                HttpStatus.BAD_REQUEST.value(), "Invalid Format Exception",
                ex.getMessage());
        service.add(response);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse response = new ErrorResponse(Timestamp.valueOf(LocalDateTime.now()),
                HttpStatus.BAD_REQUEST.value(), ex.getErrorCode(), ex.getMessage());
        service.add(response);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException ex) {
        ErrorResponse response = new ErrorResponse(Timestamp.valueOf(LocalDateTime.now()),
                HttpStatus.BAD_REQUEST.value(), "Method Argument Exception",
                ex.getMessage());
        service.add(response);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleException(NumberFormatException ex) {
        ErrorResponse response = new ErrorResponse(Timestamp.valueOf(LocalDateTime.now()),
                HttpStatus.BAD_REQUEST.value(), "Number Format Exception",
                ex.getMessage());
        service.add(response);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ErrorResponse response = new ErrorResponse(Timestamp.valueOf(LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "General Exception",
                ex.getMessage()
                );
        service.add(response);
        String message = "Sorry mate, something is broken :(";
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
