package com.jayplusplus.blackbox.exception;

import com.jayplusplus.blackbox.model.GenericResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.security.SignatureException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;


/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler{

    /**
     * Handle global exception response entity.
     *
     * @param ge the ge
     * @return the response entity
     */
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<GenericResponse> handleGlobalException(GlobalException ge){
        GenericResponse response = new GenericResponse(ge.getCode(),ge.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * Handle bad credentials exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<GenericResponse> handleBadCredentialsException(BadCredentialsException e) {
        GenericResponse response = new GenericResponse(01, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }


    /**
     * Handle exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler({ConstraintViolationException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<GenericResponse> handleException(Exception e) {
        GenericResponse response = new GenericResponse(02, "Required request body is missing");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    /**
     * Handle sql integrity exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<GenericResponse> handleSQLIntegrityException(SQLIntegrityConstraintViolationException e) {
        GenericResponse response = new GenericResponse(03, "Username already taken");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * Handle no such element exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<GenericResponse> handleNoSuchElementException(NoSuchElementException e) {
        GenericResponse response = new GenericResponse(03, "Can't find user");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<GenericResponse> handleNoSuchElementException(SignatureException e) {
        GenericResponse response = new GenericResponse(04, "Invalid token");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

}
