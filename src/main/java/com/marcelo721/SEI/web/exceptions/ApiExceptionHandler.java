package com.marcelo721.SEI.web.exceptions;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.marcelo721.SEI.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result){

        log.error("Api Error -", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Invalid Arguments", result));

    }

    @ExceptionHandler(EmailUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> emailUniqueViolationException(RuntimeException ex,
                                                                      HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT,ex.getMessage()));

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex,
                                                                HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND,ex.getMessage()));

    }

    @ExceptionHandler(SemesterInvalidException.class)
    public ResponseEntity<ErrorMessage> semesterInvalidException(RuntimeException ex,
                                                                HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,ex.getMessage()));

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> HttpMessageNotReadableException(RuntimeException ex,
                                                                        HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY,ex.getMessage()));

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> integrityViolationException(RuntimeException ex,
                                                                    HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT,ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request){

        log.error("Api error !",ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,ex.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request){

        log.error("Api error !",ex);

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.METHOD_NOT_ALLOWED,"HTTP method not supported: " + ex.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> NoSuchElementException( NoSuchElementException ex, HttpServletRequest request){

        log.error("Api error !",ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage() + " Esse usuário existe"));
    }


    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ErrorMessage> jWTCreationException(JWTCreationException ex, HttpServletRequest request){

        log.error("Api error !",ex);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNAUTHORIZED,"error generating token :" + ex.getMessage()));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> SQLIntegrityConstraintViolationException( SQLIntegrityConstraintViolationException ex, HttpServletRequest request){

        log.error("Api error !",ex);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT,"Conflict when inserting record: duplicate entry" + ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> accessDeniedException(AccessDeniedException ex, HttpServletRequest request){

        log.error("Api error !",ex);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.FORBIDDEN,"Not allowed:" + ex.getMessage()));
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> passwordInvalidException(RuntimeException ex,
                                                                 HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY,ex.getMessage()));

    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorMessage> expiredTokenException(RuntimeException ex,
                                                                 HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,ex.getMessage()));

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> IllegalArgumentException(RuntimeException ex,
                                                              HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,ex.getMessage()));

    }

    @ExceptionHandler(AccountNotEnabledException.class)
    public ResponseEntity<ErrorMessage> AccountNotEnabledException(RuntimeException ex,
                                                                 HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNAUTHORIZED,ex.getMessage()));

    }

    @ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<ErrorMessage> OtpExpiredException(RuntimeException ex,
                                                                   HttpServletRequest request){
        log.error("Api Error", ex);

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.EXPECTATION_FAILED,ex.getMessage()));

    }
}
