package com.backend.la.backendloveafrica.controllers.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.security.UserDetailsServiceImp;
import com.backend.la.backendloveafrica.models.dtos.security.AuthLoginRequestDTO;
import com.backend.la.backendloveafrica.models.dtos.security.AuthResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
// @CrossOrigin(origins = {"https://www.myback.com.ar","http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/", "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173"})
public class AuthenticationController {

  @Autowired
  private UserDetailsServiceImp userDetailsService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {

    return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
  }

}
