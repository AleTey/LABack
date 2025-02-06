package com.backend.la.backendloveafrica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.ITiraService;

@RestController
@RequestMapping("/tiras")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class TiraController {

  @Autowired
  private ITiraService tiraService;

  @GetMapping("")
  public ResponseEntity<?> FindAllTiras() {
    return ResponseEntity.ok().body(tiraService.findAllTiras());
  }

}
