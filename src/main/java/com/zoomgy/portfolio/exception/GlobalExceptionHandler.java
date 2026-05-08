package com.zoomgy.portfolio.exception;

import com.zoomgy.portfolio.utils.CustomResponseBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.zoomgy.portfolio.utils.CustomResponseBuilder.buildError;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(buildError(errors,"Validations Failed",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEntry(
            DataIntegrityViolationException ex
    ) {
        return new ResponseEntity<>(
                buildError("Duplicate Value Found",HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUsernameNotFoundException(UsernameNotFoundException e){
        return new ResponseEntity<>(buildError(e.getMessage(),HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EducationNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEducationNotFoundException(EducationNotFoundException e){
        return new ResponseEntity<>(buildError(e.getMessage(),HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExperienceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEducationNotFoundException(ExperienceNotFoundException e){
        return new ResponseEntity<>(buildError(e.getMessage(),HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String,Object>> handleEmailNotFoundException(InvalidEmailException e){
        return new ResponseEntity<>(buildError(e.getMessage(),HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String,Object>> handlePasswordNotCorrectException(InvalidPasswordException e){
        return new ResponseEntity<>(buildError(e.getMessage(),HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFoundException(UserNotFoundException e){
        return new ResponseEntity<>(buildError(e.getMessage(),HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleProjectNotFoundException(ProjectNotFoundException e){
        return new ResponseEntity<>(buildError(e.getMessage(),HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFoundException(Exception e){
        return new ResponseEntity<>(buildError(e.getMessage(),HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);

    }
}
