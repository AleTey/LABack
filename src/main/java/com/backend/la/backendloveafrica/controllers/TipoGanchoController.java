package com.backend.la.backendloveafrica.controllers;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.models.enums.TipoGancho;

@RestController
@RequestMapping("/tiposGancho")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class TipoGanchoController {

  @GetMapping("")
  public ResponseEntity<?> getAllTiposGancho() {
    return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(TipoGancho.values()));
  }

}
