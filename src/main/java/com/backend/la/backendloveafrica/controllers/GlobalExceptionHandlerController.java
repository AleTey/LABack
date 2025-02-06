package com.backend.la.backendloveafrica.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.backend.la.backendloveafrica.Exception.CustomAccessDeniedException;
import com.backend.la.backendloveafrica.Exception.IllegalStatusException;
import com.backend.la.backendloveafrica.Exception.InsufficientInformationToCalculateCost;
import com.backend.la.backendloveafrica.Exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlerController {

  @ExceptionHandler(IllegalStatusException.class)
  public ResponseEntity<String> handleIllegalStatusException(IllegalStatusException ex, WebRequest request) {
    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(InsufficientInformationToCalculateCost.class)
  public ResponseEntity<String> handleInsufficientInformationToCalculateCost(InsufficientInformationToCalculateCost ex,
      WebRequest request) {
    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(CustomAccessDeniedException.class)
  public ResponseEntity<String> handlerCustomAccessDeniedException(CustomAccessDeniedException ex, WebRequest request) {
    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }
  // @ExceptionHandler(AccessDeniedExceptio.class)
  // public ResponseEntity<?>
  // handleMethodArgumentNotValidException(AccessDeniedException e,
  // HttpServletRequest request) {
  // ApiError apiError = new ApiError();
  // apiError.setBackendMessage(e.getLocalizedMessage());
  // apiError.setUrl(request.getRequestURL().toString());
  // apiError.setMethod(request.getMethod());
  // apiError.setTimestamp(LocalDateTime.now());
  // apiError.setMessage("Error en petición Enviada");

  // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
  // }

}
