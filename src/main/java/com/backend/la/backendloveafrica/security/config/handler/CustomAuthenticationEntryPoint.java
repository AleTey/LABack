package com.backend.la.backendloveafrica.security.config.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.backend.la.backendloveafrica.Exception.JwtTokenExpiredException;
import com.backend.la.backendloveafrica.models.dtos.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {

    ApiError apiError = new ApiError();

    if (authException instanceof JwtTokenExpiredException) {
      apiError.setBackendMessage(authException.getLocalizedMessage());
      apiError.setMessage("JWT Token has expired. Please login again.");
    } else {
      apiError.setBackendMessage(authException.getLocalizedMessage());
      apiError.setMessage("Please Login");
    }

    apiError.setTimestamp(LocalDateTime.now());
    apiError.setUrl(request.getRequestURL().toString());
    apiError.setMethod(request.getMethod());

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    String apiErroAsJson = objectMapper.writeValueAsString(apiError);
    response.getWriter().write(apiErroAsJson);
  }

}
