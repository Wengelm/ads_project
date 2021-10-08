package com.senla.ads.exception;

import org.apache.kafka.common.requests.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler({MyEntityNotFoundException.class, EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(RuntimeException ex, WebRequest request) {
        ApiErrorMessage apiError = new ApiErrorMessage("Not found with this id.");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserAlreadyExistAuthenticationException.class, EntityExistsException.class})
    protected ResponseEntity<Object> handleEntityAllReadyExists(RuntimeException ex, WebRequest request) {
        ApiErrorMessage apiError = new ApiErrorMessage("User with this email or login exist.");
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders
            headers, HttpStatus status, WebRequest request) {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage("Incorrect JSON");
        logger.debug(ex.getMessage());
        return new ResponseEntity<>(apiErrorMessage, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorMessage("No found API."), status);
    }
}
