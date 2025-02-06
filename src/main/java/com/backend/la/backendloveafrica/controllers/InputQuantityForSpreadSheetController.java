package com.backend.la.backendloveafrica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.IInputQuantityForSpreadSheetService;
import com.backend.la.backendloveafrica.Services.IPreparationSpreadSheetService;
import com.backend.la.backendloveafrica.models.entities.InputQuantityForSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.PreparationSpreadSheet;

@RestController
@RequestMapping("/input-quantity")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class InputQuantityForSpreadSheetController {

  @Autowired
  private IInputQuantityForSpreadSheetService inputQuantityForSpreadSheetService;

  @Autowired
  private IPreparationSpreadSheetService preparationSpreadSheetService;

  @GetMapping("/{id}")
  public ResponseEntity<List<InputQuantityForSpreadSheet>> inputsCalculator(@PathVariable Long id) {
    Optional<PreparationSpreadSheet> o = preparationSpreadSheetService.findById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok(inputQuantityForSpreadSheetService.calculateInputs(o.get()));
    }
    return ResponseEntity.notFound().build();

  }

}
