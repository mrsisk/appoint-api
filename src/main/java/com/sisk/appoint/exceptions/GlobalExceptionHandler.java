package com.sisk.appoint.exceptions;

import com.sisk.appoint.model.ConstraintField;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Map<String, ConstraintField> constraints = Map.of(
            "email_unique_idx", new ConstraintField("email" ,"Email already exist"),
            "uid_unique_idx", new ConstraintField("uid", "uid already exist")
    );
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public Map<String, String> handleJwt(ExpiredJwtException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, String> handle(NoSuchElementException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> messageNotReadable(HttpMessageNotReadableException exception){
        Map<String, String> errors = new HashMap<>();
        System.out.println("bad request " + exception.getLocalizedMessage());
        errors.put("message", exception.getLocalizedMessage());
        return errors;
    }

//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Map<String, String> handleControllerException(AuthenticationException exception) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("message", exception.getLocalizedMessage());
//        return errors;
//    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> auth(BadCredentialsException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> runTimeEx(RuntimeException exception){
        Map<String, String> errors = new HashMap<>();
        System.out.println("runtime ex " + exception.getLocalizedMessage());
        errors.put("message", exception.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegrityEx(DataIntegrityViolationException exception){
        Map<String, String> errors = new HashMap<>();
        String errorMsg = exception.getLocalizedMessage();
        if (errorMsg != null){
            for (Map.Entry<String, ConstraintField> entry : constraints.entrySet()){
                if (errorMsg.contains(entry.getKey())){
                    errors.put(entry.getValue().name(), entry.getValue().message());
                }
            }
        }

       // errors.put("message", exception.getLocalizedMessage());
        return errors;
    }
}