package com.backend.la.backendloveafrica.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.IControleSpreadSheetService;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ControlSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.ControlSpreadSheet;

@RestController
@RequestMapping("/control-spreadsheet")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class ControlSpreadSheetController {

  @Autowired
  private IControleSpreadSheetService controleSpreadSheetService;

  @GetMapping("/dto/{id}")
  public ResponseEntity<ControlSpreadSheetDTO> getDtoById(@PathVariable Long id) {
    Optional<ControlSpreadSheetDTO> o = controleSpreadSheetService.getDtoById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<ControlSpreadSheetDTO> updateControlSpreadSheet(
      @RequestBody ControlSpreadSheet controlSpreadSheet) {
    Optional<ControlSpreadSheetDTO> o = controleSpreadSheetService.update(controlSpreadSheet);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }
}
